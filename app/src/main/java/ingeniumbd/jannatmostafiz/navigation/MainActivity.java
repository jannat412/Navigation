package ingeniumbd.jannatmostafiz.navigation;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_phone) {
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "+8801794778842")));
            return true;
        }
        if (id == R.id.action_email) {
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setType("text/plain");
            final PackageManager pm = getPackageManager();
            final List<ResolveInfo> matches = pm.queryIntentActivities(intent, 0);
            ResolveInfo best = null;
            for (final ResolveInfo info : matches)
                if (info.activityInfo.packageName.endsWith(".gm") ||
                        info.activityInfo.name.toLowerCase().contains("gmail")) best = info;
            if (best != null)
            {
                intent.setClassName(best.activityInfo.packageName, best.activityInfo.name);

                String[] TO = {"shakilmehedi3@gmail.com"};
                String[] CC = {"shakilmehedi3@gmail.com"};
                intent.setData(Uri.parse("mailto:"));
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, TO);
                intent.putExtra(Intent.EXTRA_CC, CC);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Contract with Shakil");
                intent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");
                startActivity(intent);
            }
            else {
                Intent intent1 = new Intent(Intent.ACTION_SEND);

                String[] TO = {"shakilmehedi3@gmail.com"};
                String[] CC = {"shakilmehedi3@gmail.com"};
                intent1.setData(Uri.parse("mailto:"));
                intent1.setType("text/plain");
                intent1.putExtra(Intent.EXTRA_EMAIL, TO);
                intent1.putExtra(Intent.EXTRA_CC, CC);
                intent1.putExtra(Intent.EXTRA_SUBJECT, "Contract with Shakil");
                intent1.putExtra(Intent.EXTRA_TEXT, "Email message goes here");
                startActivity(intent1);
            }
            return true;
        }
        if (id == R.id.action_facebook) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/ingeniumbd/")));
            return true;
        }
        if (id == R.id.action_google) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/u/2/117505008096786718810")));
            return true;
        }
        if (id == R.id.action_exit) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Exit Application?");
            alertDialogBuilder
                    .setMessage("Click yes to exit!")
                    .setCancelable(false)
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    moveTaskToBack(true);
                                    android.os.Process.killProcess(android.os.Process.myPid());
                                    System.exit(1);
                                }
                            })

                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentManager fm = getFragmentManager();
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            fm.beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
        } else if (id == R.id.nav_gallery) {
            fm.beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
        } else if (id == R.id.nav_department) {
            fm.beginTransaction().replace(R.id.content_frame, new DepartmentFragment()).commit();
        } else if (id == R.id.nav_class) {
            fm.beginTransaction().replace(R.id.content_frame, new ClassFragment()).commit();
        } else if (id == R.id.nav_semester) {
            fm.beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void buttonOne(View view) {
        Intent intent = new Intent(MainActivity.this, BottomBar.class);
        startActivity(intent);
    }
}
