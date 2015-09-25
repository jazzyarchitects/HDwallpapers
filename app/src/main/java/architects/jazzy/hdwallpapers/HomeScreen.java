package architects.jazzy.hdwallpapers;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {
    private static final String TAG="HomeScreen";
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new ImageRetriever().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class ImageRetriever extends AsyncTask<Void, Void, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect("https://www.pexels.com").get();
                Elements articles=doc.select(".photos__photo");
                ArrayList<String> imgUrls=new ArrayList<>();
                for(Element element:articles){
                    Log.e(TAG,"ELements: "+element.toString());
                    Element imageElement=element.select("img").first();
                    imgUrls.add(imageElement.attr("src").replace("-medium","-small"));
                }
                return imgUrls;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<String> imgUrls) {
            super.onPostExecute(imgUrls);
            if(imgUrls==null){
                Log.e(TAG,"Null url");
                return;
            }
            if(imgUrls.isEmpty()){
                Log.e(TAG,"Empty url");
                return;
            }

            Log.e(TAG,"Urls fetched: "+imgUrls.size());
            ImageListAdapter adapter=new ImageListAdapter(HomeScreen.this,imgUrls);
            recyclerView.setAdapter(adapter);

        }
    }

}
