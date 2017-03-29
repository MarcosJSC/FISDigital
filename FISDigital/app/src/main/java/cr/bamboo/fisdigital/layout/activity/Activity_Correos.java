package cr.bamboo.fisdigital.layout.activity;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.widget.EditText;
import android.widget.ListView;

import cr.bamboo.fisdigital.R;
import cr.bamboo.fisdigital.layout.adapter.Correo_ListViewAdapter;
import cr.bamboo.fisdigital.layout.fragment.DashboardDrawer_Fragment;
import cr.bamboo.fisdigital.layout.fragment.NavigationDrawer_Fragment;
import cr.bamboo.fisdigital.logic.bo.Correo;
import cr.bamboo.fisdigital.logic.global.FISController;
import cr.bamboo.fisdigital.data.dbmodel.Opcion;

/**
 * Created by dianavalerin on 7/11/15.
 */
public class Activity_Correos extends FISActivity
        implements NavigationDrawer_Fragment.NavigationDrawerCallbacks {
    private CharSequence mTitle;
    private DashboardDrawer_Fragment mDashboardDrawerFragmentCara1;
    private NavigationDrawer_Fragment mNavigationDrawerFragmentCara1;
    FISController fisController = FISController.getInstance();

    @Override
    public void onNavigationDrawerItemSelected(Opcion opcion, int catalogId) {
    }

    private void loadData(){
        ArrayList<Correo> correosFamilia = fisController.loadCorreos();
        Correo_ListViewAdapter adapter = new Correo_ListViewAdapter(correosFamilia, this);
        ListView emailLV = ((ListView)findViewById(R.id.list_view_correos));
        emailLV.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        if(mDashboardDrawerFragmentCara1.isDrawerOpen()){
            mDashboardDrawerFragmentCara1.closeDrawer();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correos);
        final Context context = this;
        mTitle = "Correos Electrónicos";

        mDashboardDrawerFragmentCara1 = (DashboardDrawer_Fragment)
         getFragmentManager().findFragmentById(R.id.dashboard_drawer1);

        mDashboardDrawerFragmentCara1.setUp(R.id.dashboard_drawer1, (DrawerLayout) findViewById(R.id.drawer_layout_correos));

        mNavigationDrawerFragmentCara1 = (NavigationDrawer_Fragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);

//        FragmentManager fragmentManager = getFragmentManager();
//        globalFragment =  PlaceholderFragment6.newInstance(1);
//        fragmentManager.beginTransaction()
//                .replace(R.id.container6, globalFragment)
//                .commit();


        mNavigationDrawerFragmentCara1.setRetainInstance(true);

        loadData();

        Button btnAddEmail = (Button) findViewById(R.id.btn_add_email);
        Button btnFinishEmail = (Button) findViewById(R.id.btn_fin_correos);

      //  Button btnAddEmail = (Button) findViewById(R.id.btn_add_email);
        btnAddEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save email
                EditText text = (EditText) findViewById(R.id.editText_correo);
                String correo = text.getText().toString().trim();
                if (correo.isEmpty()) {
                    text.setError("Debe ingrear el correo");
                } else {
                    String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                    Pattern p = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
                    Matcher m = p.matcher(correo);
                    if (!m.matches()) {
                        text.setError("El formato de correo se encuentra inválido.");
                    } else {
                        text.setError(null);
                        fisController.saveCorreo(correo);
                        text.getText().clear();
                        loadData();
                        //emailLV.deferNotifyDataSetChanged();
                        //adapter.notifyDataSetChanged();
                        //adapter.swapItems(correosFamilia);
                    }

                }
            }
        });

        //btn_fin_correos
        btnFinishEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mDashboardDrawerFragmentCara1.saveObservaciones();
                Intent openStep = new Intent(context, Activity_ListarFamilias.class);
                context.startActivity(openStep);
            }
        });
        restoreActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fiscara1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }
   }
