package restaurantssix.android.curso.com.example.ssix.restaurantversionssix;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private ListView listView;
    private WebView webView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display a indeterminate progress bar on title bar
      requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        this.setContentView(R.layout.main);


        this.listView = (ListView) findViewById(R.id.listView);
        this.webView = (WebView) findViewById(R.id.webView);

        List items = new ArrayList();
        items.add(new Item(R.drawable.burger_king, "Burger King",
                "http://www.burgerking.com.mx/"));
        items.add(new Item(R.drawable.pizza_hut, "Pizza Hut",
                "http://www.pizzahut.com.mx/"));
        items.add(new Item(R.drawable.bbt_merida, "BBT Merida",
                "http://www.semeantoja.com/BBT/menu"));
        items.add(new Item(R.drawable.mc_donals, "Mc Donal's",
                "http://www.tiendeo.mx/merida/mcdonalds"));
        items.add(new Item(R.drawable.los_trompos, "Los Trompos",
                "http://lostrompos.com.mx/"));
        items.add(new Item(R.drawable.fridays, "Fridays",
                "http://www.fridaysmerida.com.mx/"));
        items.add(new Item(R.drawable.little_caesars,
                "Little Caesars", "http://littlecaesars.com.mx/Localizador/Detalles/tabid/3003/s/Yucatan/Default.aspx?id=NDExODAwMDI%3D"));


        // Sets the data behind this ListView
        this.listView.setAdapter(new ItemAdapter(this, items));

        // Register a callback to be invoked when an item in this AdapterView
        // has been clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view,
                                    int position, long arg) {

                // Sets the visibility of the indeterminate progress bar in the
                // title
                setProgressBarIndeterminateVisibility(false);
                // Show progress dialog
                progressDialog = ProgressDialog.show(MainActivity.this,
                        "ProgressDialog", "Loading!");

                // Tells JavaScript to open windows automatically.
                webView.getSettings().setJavaScriptEnabled(true);
                // Sets our custom WebViewClient.
                webView.setWebViewClient(new myWebClient());
                // Loads the given URL
                Item item = (Item) listView.getAdapter().getItem(position);
                webView.loadUrl(item.getUrl());
            }
        });

    }

    private class myWebClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // Load the given URL on our WebView.
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            // When the page has finished loading, hide progress dialog and
            // progress bar in the title.
            super.onPageFinished(view, url);
            setProgressBarIndeterminateVisibility(false);
            progressDialog.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
