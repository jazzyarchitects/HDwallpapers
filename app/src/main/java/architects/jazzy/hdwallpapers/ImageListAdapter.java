package architects.jazzy.hdwallpapers;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jibin_ism on 25-Sep-15.
 */
public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder>{

    private static String TAG="ImageListAdapter";
    Context context;
    ArrayList<String> urls;

    public ImageListAdapter(Context context,ArrayList<String> urls) {
        super();
        this.context=context;
        this.urls=urls;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.image_list_adapter,viewGroup,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        final String url=urls.get(position);
        Picasso.with(context)
                .load(Uri.parse(url))
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.imageView);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Thread thread=new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        final ProgressDialog dialog=new ProgressDialog(context);
//                        dialog.setMessage("Fetching wallpaper and setting");
//                        dialog.setIndeterminate(true);
//                        dialog.setCancelable(true);
//                        viewHolder.imageView.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                dialog.show();
//                            }
//                        });
                        WallpaperManager wallpaperManager=WallpaperManager.getInstance(context);
                        try {
                            Bitmap bitmap = Picasso.with(context).load(Uri.parse(url.replace("-small", "-medium"))).get();
                            wallpaperManager.setBitmap(bitmap);
                        }catch (IOException e){
                            e.printStackTrace();
                        }
//                        viewHolder.imageView.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                if(dialog!=null){
//                                    if(dialog.isShowing()){
//                                        dialog.dismiss();
//                                    }
//                                }
//                            }
//                        });
                    }
                });
                thread.start();
            }
        });
        Log.e(TAG, "Url is :"+url);

    }

    @Override
    public int getItemCount() {
        return urls==null?0:urls.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
