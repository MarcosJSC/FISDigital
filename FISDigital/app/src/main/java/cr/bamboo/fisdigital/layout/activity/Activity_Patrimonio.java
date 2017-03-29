package cr.bamboo.fisdigital.layout.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import cr.bamboo.fisdigital.R;
import cr.bamboo.fisdigital.layout.fragment.DashboardDrawer_Fragment;
import cr.bamboo.fisdigital.layout.fragment.NavigationDrawer_Fragment;
import cr.bamboo.fisdigital.logic.bo.Patrimonio;
import cr.bamboo.fisdigital.logic.global.FISController;
import cr.bamboo.fisdigital.data.dbmodel.Opcion;

public class Activity_Patrimonio extends FISActivity
        implements NavigationDrawer_Fragment.NavigationDrawerCallbacks {

    /**
               * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
               */
    private NavigationDrawer_Fragment mNavigationDrawerFragmentCara1;
    private DashboardDrawer_Fragment mDashboardDrawerFragmentCara1;

    FISController fisController = FISController.getInstance();

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patrimonio);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            try {
                fisController.loadPatrimonio();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        mNavigationDrawerFragmentCara1 = (NavigationDrawer_Fragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);

        mDashboardDrawerFragmentCara1 = (DashboardDrawer_Fragment)
                getFragmentManager().findFragmentById(R.id.dashboard_drawer1);

        mDashboardDrawerFragmentCara1.getArguments().putString("textoSecciones", "9/10");
        mDashboardDrawerFragmentCara1.getArguments().putString("textoPreguntas", "58/61");
        mDashboardDrawerFragmentCara1.setData();

        mTitle = "Patrimonio";

        // Set up the drawer.
        mNavigationDrawerFragmentCara1.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout8));

        mDashboardDrawerFragmentCara1.setUp(R.id.dashboard_drawer1, (DrawerLayout) findViewById(R.id.drawer_layout8));

        FragmentManager fragmentManager = getFragmentManager();
        globalFragment =  PlaceholderFragment8.newInstance(1);
        fragmentManager.beginTransaction()
                .replace(R.id.container8, globalFragment)
                .commit();


        mNavigationDrawerFragmentCara1.setRetainInstance(true);
    }

    private void setText(String description, TextView tv){
        tv.setText(description);
        tv.setTextAppearance(globalFragment.getView().getContext(), R.style.FISTextView);
        tv.setBackgroundResource(R.drawable.textview_border);
    }

    @Override
    public void onNavigationDrawerItemSelected(Opcion opcion, int catalogId) {

        //Tenencia Vivienda
        if(catalogId == 0) {

            ((GridLayout) globalFragment.getView().findViewById(R.id.gridMontoPropia)).setVisibility(View.GONE);
            ((GridLayout) globalFragment.getView().findViewById(R.id.gridMontoAlquilada)).setVisibility(View.GONE);
            ((GridLayout) globalFragment.getView().findViewById(R.id.gridMontoPrecario)).setVisibility(View.GONE);

            setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvTenenciaVivienda));
            fisController.getPatrimonio().setTenenciaVivenda(opcion);

            if(fisController.getPatrimonio().getIdTenenciaVivienda().equals("2")){
                ((GridLayout) globalFragment.getView().findViewById(R.id.gridMontoPropia)).setVisibility(View.VISIBLE);
            }
            if(fisController.getPatrimonio().getIdTenenciaVivienda().equals("3")){
                ((GridLayout) globalFragment.getView().findViewById(R.id.gridMontoAlquilada)).setVisibility(View.VISIBLE);
            }
            if(fisController.getPatrimonio().getIdTenenciaVivienda().equals("8")){
                ((GridLayout) globalFragment.getView().findViewById(R.id.gridMontoPrecario)).setVisibility(View.VISIBLE);
            }

            if(!fisController.getPatrimonio().getIdTenenciaVivienda().equals("1") && !fisController.getPatrimonio().getIdTenenciaVivienda().equals("2")){
                setText("No Aplica.", (TextView) globalFragment.getView().findViewById(R.id.tvAdquirioVivienda));
                ((Button) globalFragment.getView().findViewById(R.id.btnAdquirioVivienda)).setEnabled(false);
                fisController.getPatrimonio().setIdAdquirioVivienda("");
            }else{
                setText("", (TextView) globalFragment.getView().findViewById(R.id.tvAdquirioVivienda));
                ((Button) globalFragment.getView().findViewById(R.id.btnAdquirioVivienda)).setEnabled(true);
                fisController.getPatrimonio().setIdAdquirioVivienda("");
            }
        }

        //Adquirio Vivienda
        if(catalogId == 1) {
            setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvAdquirioVivienda));
            fisController.getPatrimonio().setAdquirioVivienda(opcion);
        }

        //Produccion Para
        if(catalogId == 2) {
            setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvProduccionPara));
            fisController.getPatrimonio().setProduccionPara(opcion);
        }

    }
    public PlaceholderFragment8 globalFragment;
    @NonNull
    @Override
    public FragmentManager getFragmentManager() {
        return super.getFragmentManager();
    }


    public int currentItem = 0;
    public int[] memoryScrollPos= new int[]{0,0};

    //public EditText txtField;
    public void onSectionAttached(int number) {

        /* When something in the right menu is clicked then this is called and we can get the position of the row clicked */

        currentItem=number;

    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragmentCara1.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.fiscara1, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_settings:
                // clear instance object
                Intent openStep = new Intent(this, Activity_Login.class);
                // remove the back stack! Super important stuff, so the user wont go back
                // to this page by clicking back
                openStep.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityForResult(openStep, 0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment8 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        FISController fisController = FISController.getInstance();

        View globalRootView;
        ScrollView sv;
        String[] errorMessages = new String[]{
                "Debe seleccionar una opción de Tenenecia de la Vivienda.",
                "Debe seleccionar una opción de Cómo adquirió la Vivienda.",
                "Monto mensual de la hipoteca requerido.",
                "Debe seleccionar una opción.",
                "Monto mensual del alquiler requerido.",
                "Debe seleccionar una opción.",
                "Monto del alquiler requerido.",
                "Area del Lote requerida.",
                "Area de otras construcciones requerida.",
                "Area de Finca/Parcela requerida.",
                "Debe seleccionar una opción de Producción Para",
                "El número del telefono no puede estar en blanco.",
                "El monto del telefono no puede estar en blanco.",
                "El Año del Vehículo no puede estar en blanco.",
                "El Año del Vehículo no puede estar en blanco.",
                "El Año de Motocicleta no puede estar en blanco.",
                "El Número de Cabezas no puede estar en blanco.",
                "El Número de Cabezas no puede estar en blanco.",
                "La cantidad no puede estar en blanco ni ser 0.",
                "El Año debe estar entre 1900 y 2050.",
                "El número esta fuera del rango permitido."
        };

        // To validate in there are errors in the form
        boolean areErrorsInForm = false;


        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        //NavigationDrawer_Fragment parentNavigationDrawerFragment;

        //public static PlaceholderFragment newInstance(int sectionNumber, NavigationDrawer_Fragment mNavigationDrawerFragment) {
        public static PlaceholderFragment8 newInstance(int sectionNumber) {
            PlaceholderFragment8 fragment = new PlaceholderFragment8();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment8() {
        }

        Activity_Patrimonio parentActivity;
        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {
            parentActivity = ((Activity_Patrimonio) getActivity());

            globalRootView= inflater.inflate(R.layout.fragment_patrimonio, container, false);
            Init(globalRootView);
            return globalRootView;
        }

        void Init(final View v){
            sv = (ScrollView)v.findViewById(R.id.svGeneralCara8);
           final ArrayList<CheckBox> checkboxesBienes = getCheckboxes(v);

            // One for each button/dropdownlist
            v.findViewById(R.id.btnTenenciaVivienda).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_Patrimonio localAct = ((Activity_Patrimonio) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(Patrimonio.getOpcionesTenenciaVivienda(), 0);
                    parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};
                }
            });

            v.findViewById(R.id.btnAdquirioVivienda).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_Patrimonio localAct = ((Activity_Patrimonio) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(Patrimonio.getOpcionesAdquirioVivienda(), 1);
                    parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};
                }
            });

            v.findViewById(R.id.btnProduccionPara).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_Patrimonio localAct = ((Activity_Patrimonio) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(Patrimonio.getOpcionesProduccionPara(), 2);
                    parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};
                }
            });


            v.findViewById(R.id.checkLote).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    if(((CheckBox)v.findViewById(R.id.checkLote)).isChecked()) {
                        v.findViewById(R.id.tvAreaLote).setVisibility(View.VISIBLE);
                        v.findViewById(R.id.etAreaLote).setVisibility(View.VISIBLE);
                        fisController.getPatrimonio().getTenenciaOtrosBienes()[0] = true;
                    }
                    else{
                        v.findViewById(R.id.tvAreaLote).setVisibility(View.GONE);
                        v.findViewById(R.id.etAreaLote).setVisibility(View.GONE);
                        ((TextView)v.findViewById(R.id.errorAreaLote)).setText("");
                        fisController.getPatrimonio().getTenenciaOtrosBienes()[0] = false;
                        fisController.getPatrimonio().setAreaM2Lote(0);
                    }
                }
            });


            v.findViewById(R.id.checkConstrucciones).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    if(((CheckBox)v.findViewById(R.id.checkConstrucciones)).isChecked()) {
                        v.findViewById(R.id.tvAreaConstrucciones).setVisibility(View.VISIBLE);
                        v.findViewById(R.id.etAreaConstrucciones).setVisibility(View.VISIBLE);
                        fisController.getPatrimonio().getTenenciaOtrosBienes()[1] = true;
                    }
                    else{
                        v.findViewById(R.id.tvAreaConstrucciones).setVisibility(View.GONE);
                        v.findViewById(R.id.etAreaConstrucciones).setVisibility(View.GONE);
                        fisController.getPatrimonio().getTenenciaOtrosBienes()[1] = false;
                        fisController.getPatrimonio().setAreaM2Construcciones1(0);
                    }
                }
            });

            v.findViewById(R.id.checkFincaParcela).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View vx) {
                if(((CheckBox)v.findViewById(R.id.checkFincaParcela)).isChecked()) {
                   v.findViewById(R.id.tvAreaFinca).setVisibility(View.VISIBLE);
                   v.findViewById(R.id.etAreaFinca).setVisibility(View.VISIBLE);
                   v.findViewById(R.id.gridProduccionPara).setVisibility(View.VISIBLE);
                    fisController.getPatrimonio().getTenenciaOtrosBienes()[2] = true;
                }
                else{
                   v.findViewById(R.id.tvAreaFinca).setVisibility(View.GONE);
                   v.findViewById(R.id.etAreaFinca).setVisibility(View.GONE);
                   v.findViewById(R.id.gridProduccionPara).setVisibility(View.GONE);
                    fisController.getPatrimonio().getTenenciaOtrosBienes()[2] = false;
                    fisController.getPatrimonio().setFincaParcelaArea(0);
                }
             }
           });

            v.findViewById(R.id.checkNingunaTenencia).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    if(((CheckBox)v.findViewById(R.id.checkNingunaTenencia)).isChecked()) {
                        ((CheckBox)v.findViewById(R.id.checkLote)).setChecked(false);
                        ((CheckBox)v.findViewById(R.id.checkLote)).setEnabled(false);
                        ((CheckBox)v.findViewById(R.id.checkFincaParcela)).setChecked(false);
                        ((CheckBox)v.findViewById(R.id.checkFincaParcela)).setEnabled(false);
                        ((CheckBox)v.findViewById(R.id.checkConstrucciones)).setChecked(false);
                        ((CheckBox)v.findViewById(R.id.checkConstrucciones)).setEnabled(false);
                        v.findViewById(R.id.tvAreaFinca).setVisibility(View.GONE);
                        v.findViewById(R.id.etAreaFinca).setVisibility(View.GONE);
                        v.findViewById(R.id.gridProduccionPara).setVisibility(View.GONE);
                        v.findViewById(R.id.tvAreaConstrucciones).setVisibility(View.GONE);
                        v.findViewById(R.id.etAreaConstrucciones).setVisibility(View.GONE);
                        v.findViewById(R.id.tvAreaLote).setVisibility(View.GONE);
                        v.findViewById(R.id.etAreaLote).setVisibility(View.GONE);
                        ((TextView)v.findViewById(R.id.errorAreaLote)).setText("");
                        ((TextView)v.findViewById(R.id.errorConstrucciones)).setText("");
                        ((TextView)v.findViewById(R.id.errorAreaParcela)).setText("");
                        boolean[] tenenciaOtrosBienes = fisController.getPatrimonio().getTenenciaOtrosBienes();
                        for(int i=0; i<tenenciaOtrosBienes.length; i++){
                            if(i<3){
                                tenenciaOtrosBienes[i] = false;
                            }
                            else{
                                tenenciaOtrosBienes[i] = true;
                            }
                        }

                        fisController.getPatrimonio().setAreaM2Construcciones1(0);
                        fisController.getPatrimonio().setAreaM2Lote(0);
                        fisController.getPatrimonio().setFincaParcelaArea(0);

                        fisController.getPatrimonio().setTenenciaOtrosBienes(tenenciaOtrosBienes);

                    }
                    else{
                        ((CheckBox)v.findViewById(R.id.checkLote)).setEnabled(true);
                        ((CheckBox)v.findViewById(R.id.checkFincaParcela)).setEnabled(true);
                        ((CheckBox)v.findViewById(R.id.checkConstrucciones)).setEnabled(true);
                        fisController.getPatrimonio().getTenenciaOtrosBienes()[3] = false;
                    }
                }
            });

            for(int i = 0; i<=10; i++) {
                final CheckBox current = checkboxesBienes.get(i);
                final int index = i;
                if (i <= 13) {
                    current.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View vx) {
                            if (current.isChecked()) {
                                fisController.getPatrimonio().getOtrosBienes()[index] = true;
                            } else {
                                fisController.getPatrimonio().getOtrosBienes()[index] = false;
                            }
                        }

                    });

                }
            }

            v.findViewById(R.id.checkTV).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    if(((CheckBox)vx).isChecked()) {
                        v.findViewById(R.id.tvCantidadTV).setVisibility(View.VISIBLE);
                        v.findViewById(R.id.etCantidadTV).setVisibility(View.VISIBLE);
                        fisController.getPatrimonio().getOtrosBienes()[11] = true;
                    }
                    else{
                        v.findViewById(R.id.tvCantidadTV).setVisibility(View.GONE);
                        v.findViewById(R.id.etCantidadTV).setVisibility(View.GONE);
                        fisController.getPatrimonio().getOtrosBienes()[11] = false;
                    }
                }
            });

            v.findViewById(R.id.checkEquipoSonido).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    if(((CheckBox)vx).isChecked()) {
                        v.findViewById(R.id.tvCantidadEquipoSonido).setVisibility(View.VISIBLE);
                        v.findViewById(R.id.etCantidadEquipoSonido).setVisibility(View.VISIBLE);
                        fisController.getPatrimonio().getOtrosBienes()[12] = true;
                    }
                    else{
                        v.findViewById(R.id.tvCantidadEquipoSonido).setVisibility(View.GONE);
                        v.findViewById(R.id.etCantidadEquipoSonido).setVisibility(View.GONE);
                        fisController.getPatrimonio().getOtrosBienes()[12] = false;
                    }
                }
            });

            v.findViewById(R.id.checkComputadoras).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    if(((CheckBox)vx).isChecked()) {
                        v.findViewById(R.id.tvCantidadComputadoras).setVisibility(View.VISIBLE);
                        v.findViewById(R.id.etCantidadComputadoras).setVisibility(View.VISIBLE);
                        fisController.getPatrimonio().getOtrosBienes()[13] = true;
                    }
                    else{
                        v.findViewById(R.id.tvCantidadComputadoras).setVisibility(View.GONE);
                        v.findViewById(R.id.etCantidadComputadoras).setVisibility(View.GONE);
                        fisController.getPatrimonio().getOtrosBienes()[13] = false;
                    }
                }
            });

            v.findViewById(R.id.checkTelefonoResidencial).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    if(((CheckBox)v.findViewById(R.id.checkTelefonoResidencial)).isChecked()) {
                        v.findViewById(R.id.tvNumeroResidencial).setVisibility(View.VISIBLE);
                        v.findViewById(R.id.etNumeroResidencial).setVisibility(View.VISIBLE);
                        v.findViewById(R.id.tvCostoResidencial).setVisibility(View.VISIBLE);
                        v.findViewById(R.id.etCostoResidencial).setVisibility(View.VISIBLE);
                        fisController.getPatrimonio().getOtrosBienes()[14] = true;
                    }
                    else{
                        v.findViewById(R.id.tvNumeroResidencial).setVisibility(View.GONE);
                        v.findViewById(R.id.etNumeroResidencial).setVisibility(View.GONE);
                        v.findViewById(R.id.tvCostoResidencial).setVisibility(View.GONE);
                        v.findViewById(R.id.etCostoResidencial).setVisibility(View.GONE);
                        fisController.getPatrimonio().getOtrosBienes()[14] = false;
                    }
                }
            });

            v.findViewById(R.id.checkTelefonoCelular).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    if(((CheckBox)v.findViewById(R.id.checkTelefonoCelular)).isChecked()) {
                        v.findViewById(R.id.tvNumeroCelular).setVisibility(View.VISIBLE);
                        v.findViewById(R.id.etNumeroCelular).setVisibility(View.VISIBLE);
                        v.findViewById(R.id.tvCostoCelular).setVisibility(View.VISIBLE);
                        v.findViewById(R.id.etCostoCelular).setVisibility(View.VISIBLE);
                        v.findViewById(R.id.tvCantidadCelular).setVisibility(View.VISIBLE);
                        v.findViewById(R.id.etCantidadCelular).setVisibility(View.VISIBLE);
                        fisController.getPatrimonio().getOtrosBienes()[15] = true;
                    }
                    else{
                        v.findViewById(R.id.tvNumeroCelular).setVisibility(View.GONE);
                        v.findViewById(R.id.etNumeroCelular).setVisibility(View.GONE);
                        v.findViewById(R.id.tvCostoCelular).setVisibility(View.GONE);
                        v.findViewById(R.id.etCostoCelular).setVisibility(View.GONE);
                        v.findViewById(R.id.tvCantidadCelular).setVisibility(View.GONE);
                        v.findViewById(R.id.etCantidadCelular).setVisibility(View.GONE);
                        fisController.getPatrimonio().getOtrosBienes()[15] = false;
                    }
                }
            });

            v.findViewById(R.id.checkTelefonoOtro).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    if(((CheckBox)vx).isChecked()) {
                        v.findViewById(R.id.tvNumeroOtro).setVisibility(View.VISIBLE);
                        v.findViewById(R.id.etNumeroOtro).setVisibility(View.VISIBLE);
                        fisController.getPatrimonio().getOtrosBienes()[21] = true;
                    }
                    else{
                        v.findViewById(R.id.tvNumeroOtro).setVisibility(View.GONE);
                        v.findViewById(R.id.etNumeroOtro).setVisibility(View.GONE);
                        fisController.getPatrimonio().getOtrosBienes()[21] = false;
                    }
                }
            });

            v.findViewById(R.id.checkVehiculoPersonal).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    if(((CheckBox)v.findViewById(R.id.checkVehiculoPersonal)).isChecked()) {
                        v.findViewById(R.id.tvAnoVehiculoPersonal).setVisibility(View.VISIBLE);
                        v.findViewById(R.id.etAnoVehiculoPersonal).setVisibility(View.VISIBLE);
                        v.findViewById(R.id.tvCantidadVehiculoPersonal).setVisibility(View.VISIBLE);
                        v.findViewById(R.id.etCantidadVehiculoPersonal).setVisibility(View.VISIBLE);
                        fisController.getPatrimonio().getOtrosBienes()[16] = true;
                    }
                    else{
                        v.findViewById(R.id.tvAnoVehiculoPersonal).setVisibility(View.GONE);
                        v.findViewById(R.id.etAnoVehiculoPersonal).setVisibility(View.GONE);
                        v.findViewById(R.id.tvCantidadVehiculoPersonal).setVisibility(View.GONE);
                        v.findViewById(R.id.etCantidadVehiculoPersonal).setVisibility(View.GONE);
                        fisController.getPatrimonio().getOtrosBienes()[16] = false;
                    }
                }
            });

            v.findViewById(R.id.checkVehiculoTrabajo).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    if(((CheckBox)v.findViewById(R.id.checkVehiculoTrabajo)).isChecked()) {
                        v.findViewById(R.id.tvAnoVehiculoTrabajo).setVisibility(View.VISIBLE);
                        v.findViewById(R.id.etAnoVehiculoTrabajo).setVisibility(View.VISIBLE);
                        fisController.getPatrimonio().getOtrosBienes()[17] = true;
                    }
                    else{
                        v.findViewById(R.id.tvAnoVehiculoTrabajo).setVisibility(View.GONE);
                        v.findViewById(R.id.etAnoVehiculoTrabajo).setVisibility(View.GONE);
                        fisController.getPatrimonio().getOtrosBienes()[17] = false;
                    }
                }
            });

            v.findViewById(R.id.checkMotocicletaTrabajo).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    if(((CheckBox)v.findViewById(R.id.checkMotocicletaTrabajo)).isChecked()) {
                        v.findViewById(R.id.tvAnoMotocicletaTrabajo).setVisibility(View.VISIBLE);
                        v.findViewById(R.id.etAnoMotocicletaTrabajo).setVisibility(View.VISIBLE);
                        fisController.getPatrimonio().getOtrosBienes()[18] = true;
                    }
                    else{
                        v.findViewById(R.id.tvAnoMotocicletaTrabajo).setVisibility(View.GONE);
                        v.findViewById(R.id.etAnoMotocicletaTrabajo).setVisibility(View.GONE);
                        fisController.getPatrimonio().getOtrosBienes()[18] = false;
                    }
                }
            });

            v.findViewById(R.id.checkGanadoBovino).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    if(((CheckBox)v.findViewById(R.id.checkGanadoBovino)).isChecked()) {
                        v.findViewById(R.id.tvNumeroCabezasBovino).setVisibility(View.VISIBLE);
                        v.findViewById(R.id.etNumeroCabezasBovino).setVisibility(View.VISIBLE);
                        fisController.getPatrimonio().getOtrosBienes()[19] = true;
                    }
                    else{
                        v.findViewById(R.id.tvNumeroCabezasBovino).setVisibility(View.GONE);
                        v.findViewById(R.id.etNumeroCabezasBovino).setVisibility(View.GONE);
                        fisController.getPatrimonio().getOtrosBienes()[19] = false;
                    }
                }
            });

            v.findViewById(R.id.checkGanadoPorcino).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    if(((CheckBox)v.findViewById(R.id.checkGanadoPorcino)).isChecked()) {
                        v.findViewById(R.id.tvNumeroCabezasPorcino).setVisibility(View.VISIBLE);
                        v.findViewById(R.id.etNumeroCabezasPorcino).setVisibility(View.VISIBLE);
                        fisController.getPatrimonio().getOtrosBienes()[20] = true;
                    }
                    else{
                        v.findViewById(R.id.tvNumeroCabezasPorcino).setVisibility(View.GONE);
                        v.findViewById(R.id.etNumeroCabezasPorcino).setVisibility(View.GONE);
                        fisController.getPatrimonio().getOtrosBienes()[20] = false;
                    }
                }
            });

            v.findViewById(R.id.checkNingunaOtrosBienes).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    if(((CheckBox)v.findViewById(R.id.checkNingunaOtrosBienes)).isChecked()) {
                        v.findViewById(R.id.tvCantidadTV).setVisibility(View.GONE);
                        v.findViewById(R.id.etCantidadTV).setVisibility(View.GONE);
                        v.findViewById(R.id.tvCantidadEquipoSonido).setVisibility(View.GONE);
                        v.findViewById(R.id.etCantidadEquipoSonido).setVisibility(View.GONE);
                        v.findViewById(R.id.tvCantidadComputadoras).setVisibility(View.GONE);
                        v.findViewById(R.id.etCantidadComputadoras).setVisibility(View.GONE);
                        v.findViewById(R.id.tvNumeroResidencial).setVisibility(View.GONE);
                        v.findViewById(R.id.etNumeroResidencial).setVisibility(View.GONE);
                        v.findViewById(R.id.tvCostoResidencial).setVisibility(View.GONE);
                        v.findViewById(R.id.etCostoResidencial).setVisibility(View.GONE);
                        v.findViewById(R.id.tvNumeroCelular).setVisibility(View.GONE);
                        v.findViewById(R.id.etNumeroCelular).setVisibility(View.GONE);
                        v.findViewById(R.id.tvCantidadCelular).setVisibility(View.GONE);
                        v.findViewById(R.id.etCantidadCelular).setVisibility(View.GONE);
                        v.findViewById(R.id.etNumeroCelular).setVisibility(View.GONE);
                        v.findViewById(R.id.etNumeroOtro).setVisibility(View.GONE);
                        v.findViewById(R.id.tvNumeroOtro).setVisibility(View.GONE);
                        v.findViewById(R.id.tvCostoCelular).setVisibility(View.GONE);
                        v.findViewById(R.id.etCostoCelular).setVisibility(View.GONE);
                        v.findViewById(R.id.tvAnoVehiculoPersonal).setVisibility(View.GONE);
                        v.findViewById(R.id.etAnoVehiculoPersonal).setVisibility(View.GONE);
                        v.findViewById(R.id.tvCantidadVehiculoPersonal).setVisibility(View.GONE);
                        v.findViewById(R.id.etCantidadVehiculoPersonal).setVisibility(View.GONE);
                        v.findViewById(R.id.tvAnoVehiculoTrabajo).setVisibility(View.GONE);
                        v.findViewById(R.id.etAnoVehiculoTrabajo).setVisibility(View.GONE);
                        ((TextView)v.findViewById(R.id.errorVehiculoTrabajo)).setText("");
                        v.findViewById(R.id.tvAnoMotocicletaTrabajo).setVisibility(View.GONE);
                        v.findViewById(R.id.etAnoMotocicletaTrabajo).setVisibility(View.GONE);
                        ((TextView)v.findViewById(R.id.errorMotocicleta)).setText("");
                        v.findViewById(R.id.tvNumeroCabezasBovino).setVisibility(View.GONE);
                        v.findViewById(R.id.etNumeroCabezasBovino).setVisibility(View.GONE);
                        ((TextView)v.findViewById(R.id.errorGanadoBovino)).setText("");
                        v.findViewById(R.id.tvNumeroCabezasPorcino).setVisibility(View.GONE);
                        v.findViewById(R.id.etNumeroCabezasPorcino).setVisibility(View.GONE);
                        ((TextView)v.findViewById(R.id.errorGanadoPorcino)).setText("");
                        fisController.getPatrimonio().getOtrosBienes()[22] = true;
                        resetCheckboxesBienes(checkboxesBienes);
                        disableCheckboxesBienes(checkboxesBienes);
                    }
                    else{
                        fisController.getPatrimonio().getOtrosBienes()[22] = false;
                        enableCheckboxesBienes(checkboxesBienes);
                    }
                }
            });


            v.findViewById(R.id.btnSiguiente).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {

                    if (!formIsValid(v)) {
                        ScrollView sv = (ScrollView) v.findViewById(R.id.svGeneralCara8);
                        sv.scrollTo(0, 0);
                        ((LinearLayout) v.findViewById(R.id.errorMsg8)).setVisibility(View.VISIBLE);
                    } else {
                        parentActivity.mDashboardDrawerFragmentCara1.saveObservaciones();
                        fisController.savePatrimonio();
                        Intent openStep = new Intent(parentActivity, Activity_ListarFamilias.class);
                        startActivity(openStep);

                    }
                }
            });


            if (fisController.getPatrimonio() != null) {
                try {
                    ((EditText) v.findViewById(R.id.etNumeroExpediente)).setText("" + fisController.getPatrimonio().getExpedienteActual());
                    ((TextView) v.findViewById(R.id.tvTenenciaVivienda)).setText(fisController.getPatrimonio().getTenenciaVivenda());
                    ((TextView) v.findViewById(R.id.tvAdquirioVivienda)).setText(fisController.getPatrimonio().getAdquirioVivienda());

                    if (fisController.getPatrimonio().getIdTenenciaVivienda().equals("2")) {
                        ((GridLayout) v.findViewById(R.id.gridMontoPropia)).setVisibility(View.VISIBLE);
                        ((EditText) v.findViewById(R.id.etMontoPropia)).setText(String.valueOf(fisController.getPatrimonio().getMontoMensualPropiaHipoteca()));
                        if(fisController.getPatrimonio().getEstadoPropiaHpoteca().equals("1")){
                            ((RadioButton)v.findViewById(R.id.rbtAlDiaPropia)).setChecked(true);

                        }
                        else if(fisController.getPatrimonio().getEstadoPropiaHpoteca().equals("2")){
                            ((RadioButton)v.findViewById(R.id.rbtConMorosidadPropia)).setChecked(true);
                        }
                    }
                    if (fisController.getPatrimonio().getIdTenenciaVivienda().equals("3")) {
                        ((GridLayout) v.findViewById(R.id.gridMontoAlquilada)).setVisibility(View.VISIBLE);
                        ((EditText) v.findViewById(R.id.etMontoAlquilada)).setText(String.valueOf(fisController.getPatrimonio().getMontoMensualAquilada()));
                        if(fisController.getPatrimonio().getEstadoAlquilada().equals("1")){
                            ((RadioButton)v.findViewById(R.id.rbtAlDiaAlquilada)).setChecked(true);
                        }
                        else if(fisController.getPatrimonio().getEstadoAlquilada().equals("2")){
                            ((RadioButton)v.findViewById(R.id.rbtConMorosidadAlquilada)).setChecked(true);
                        }
                    }
                    if (fisController.getPatrimonio().getIdTenenciaVivienda().equals("8")) {
                        ((GridLayout) v.findViewById(R.id.gridMontoPrecario)).setVisibility(View.VISIBLE);
                    }

                    if(!fisController.getPatrimonio().getIdTenenciaVivienda().equals("") && !fisController.getPatrimonio().getIdTenenciaVivienda().equals("1") && !fisController.getPatrimonio().getIdTenenciaVivienda().equals("2")){
                        ((TextView) v.findViewById(R.id.tvAdquirioVivienda)).setText("No Aplica.");
                        ((Button) v.findViewById(R.id.btnAdquirioVivienda)).setEnabled(false);
                    }

                    if (fisController.getPatrimonio().getTenenciaOtrosBienes()[0]) {
                        ((CheckBox) v.findViewById(R.id.checkLote)).performClick();
                        ((EditText) v.findViewById(R.id.etAreaLote)).setText(String.valueOf(fisController.getPatrimonio().getAreaM2Lote()));
                    }
                    if (fisController.getPatrimonio().getTenenciaOtrosBienes()[1]) {
                        ((CheckBox) v.findViewById(R.id.checkConstrucciones)).performClick();
                        ((EditText) v.findViewById(R.id.etAreaConstrucciones)).setText(String.valueOf(fisController.getPatrimonio().getAreaM2Construcciones1()));
                    }
                    if (fisController.getPatrimonio().getTenenciaOtrosBienes()[2]) {
                        ((CheckBox) v.findViewById(R.id.checkFincaParcela)).performClick();
                        ((EditText) v.findViewById(R.id.etAreaFinca)).setText(String.valueOf(fisController.getPatrimonio().getFincaParcelaArea()));
                        if (fisController.getPatrimonio().getIdProduccionPara() != "") {
                            ((TextView) v.findViewById(R.id.tvProduccionPara)).setText(fisController.getPatrimonio().getProduccionPara());
                        }
                    }
                    if (fisController.getPatrimonio().getTenenciaOtrosBienes()[3]) {
                        ((CheckBox) v.findViewById(R.id.checkNingunaTenencia)).performClick();
                    }

                    for (int i = 0; i < fisController.getPatrimonio().getOtrosBienes().length; i++) {
                        if (fisController.getPatrimonio().getOtrosBienes()[i]) {
                            checkboxesBienes.get(i).performClick();

                            switch (i) {
                                case 11:
                                    ((EditText) v.findViewById(R.id.etCantidadTV)).setText(""+fisController.getPatrimonio().getCantidadTV());
                                    break;
                                case 12:
                                    ((EditText) v.findViewById(R.id.etCantidadEquipoSonido)).setText(""+fisController.getPatrimonio().getCantidadEquipoSonido());
                                    break;
                                case 13:
                                    ((EditText) v.findViewById(R.id.etCantidadComputadoras)).setText(""+fisController.getPatrimonio().getCantidadComputadoras());
                                    break;
                                case 14:
                                    ((EditText) v.findViewById(R.id.etNumeroResidencial)).setText(fisController.getPatrimonio().getTelefonoResidencial1());
                                    ((EditText) v.findViewById(R.id.etCostoResidencial)).setText(String.valueOf(fisController.getPatrimonio().getMontoTelefonoResidencial1()));
                                    break;
                                case 15:
                                    ((EditText) v.findViewById(R.id.etNumeroCelular)).setText(fisController.getPatrimonio().getTelefonoCelular1());
                                    ((EditText) v.findViewById(R.id.etCostoCelular)).setText(String.valueOf(fisController.getPatrimonio().getMontoTelefonoCelular1()));
                                    ((EditText) v.findViewById(R.id.etCantidadCelular)).setText(""+fisController.getPatrimonio().getCantidadTelefonoCelular());
                                    break;
                                case 16:
                                    ((EditText) v.findViewById(R.id.etAnoVehiculoPersonal)).setText(""+fisController.getPatrimonio().getAnoVehiculoPersonal());
                                    ((EditText) v.findViewById(R.id.etCantidadVehiculoPersonal)).setText(""+fisController.getPatrimonio().getCantidadVehiculoPersonal());
                                    break;
                                case 17:
                                    ((EditText) v.findViewById(R.id.etAnoVehiculoTrabajo)).setText(""+fisController.getPatrimonio().getAnoVehiculoTrabajo());
                                    break;
                                case 18:
                                    ((EditText) v.findViewById(R.id.etAnoMotocicletaTrabajo)).setText(""+fisController.getPatrimonio().getAnoMotocicletaTrabajo());
                                    break;
                                case 19:
                                    ((EditText) v.findViewById(R.id.etNumeroCabezasBovino)).setText(""+fisController.getPatrimonio().getNumeroCabezasBovino());
                                    break;
                                case 20:
                                    ((EditText) v.findViewById(R.id.etNumeroCabezasPorcino)).setText(""+fisController.getPatrimonio().getNumeroCabezasPorcino());
                                    break;
                                case 21:
                                    ((EditText) v.findViewById(R.id.etNumeroOtro)).setText(fisController.getPatrimonio().getTelefonoOtro());
                                    break;
                            }
                        }

                    }


                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }



            sv.post(new Runnable(){
                public void run(){
                    sv.scrollTo(parentActivity.memoryScrollPos[0], parentActivity.memoryScrollPos[1]);
                }
            });

        }

        private void resetCheckboxesBienes(ArrayList<CheckBox> checkboxes){
            boolean[] otros = fisController.getPatrimonio().getOtrosBienes();
            for(int i=0; i<checkboxes.size(); i++){
                if(i <=21) {
                    checkboxes.get(i).setChecked(false);
                    otros[i] = false;
                }
            }
        }

        private void disableCheckboxesBienes(ArrayList<CheckBox> checkboxes){
            for(int i=0; i<checkboxes.size(); i++){
                if(i <=21) {
                    checkboxes.get(i).setEnabled(false);
                }
            }
        }

        private void enableCheckboxesBienes(ArrayList<CheckBox> checkboxes){
            for(int i=0; i<checkboxes.size(); i++){
                if(i <=21) {
                    checkboxes.get(i).setEnabled(true);
                }
            }
        }

        private ArrayList<CheckBox> getCheckboxes(View v){
            ArrayList result = new ArrayList();
            result.add((CheckBox)v.findViewById(R.id.checkRefrigerador));//0
            result.add((CheckBox)v.findViewById(R.id.checkLavadora));
            result.add((CheckBox)v.findViewById(R.id.checkVHS));
            result.add((CheckBox)v.findViewById(R.id.checkDucha));
            result.add((CheckBox)v.findViewById(R.id.checkTanque));
            result.add((CheckBox)v.findViewById(R.id.checkMicroondas));
            result.add((CheckBox)v.findViewById(R.id.checkPanga));
            result.add((CheckBox)v.findViewById(R.id.checkLancha));
            result.add((CheckBox)v.findViewById(R.id.checkMaquinariaIndustrial));
            result.add((CheckBox)v.findViewById(R.id.checkMaquinariaAgricola));
            result.add((CheckBox)v.findViewById(R.id.checkOtraMaquinaria));//10

            result.add((CheckBox)v.findViewById(R.id.checkTV));
            result.add((CheckBox)v.findViewById(R.id.checkEquipoSonido));
            result.add((CheckBox)v.findViewById(R.id.checkComputadoras));
            result.add((CheckBox)v.findViewById(R.id.checkTelefonoResidencial));
            result.add((CheckBox)v.findViewById(R.id.checkTelefonoCelular));
            result.add((CheckBox)v.findViewById(R.id.checkVehiculoPersonal));
            result.add((CheckBox)v.findViewById(R.id.checkVehiculoTrabajo));
            result.add((CheckBox)v.findViewById(R.id.checkMotocicletaTrabajo));
            result.add((CheckBox)v.findViewById(R.id.checkGanadoBovino));
            result.add((CheckBox)v.findViewById(R.id.checkGanadoPorcino));
            result.add((CheckBox)v.findViewById(R.id.checkTelefonoOtro));
            result.add((CheckBox)v.findViewById(R.id.checkNingunaOtrosBienes));//22

            return result;
        }


        void setError(int idErrorMsg, TextView component) {
            component.setText(errorMessages[idErrorMsg]);
            component.setTextAppearance(globalRootView.getContext(), R.style.FISTextViewError);
            component.setBackgroundResource(R.drawable.textview_border_error);
        }

        void setError(int idErrorMsg, EditText component) {
            component.setError(errorMessages[idErrorMsg]);
        }

        boolean formIsValid(View v) {


            v = globalRootView;
            boolean error = false;

            EditText et = null;
            TextView tv = null;

            et = ((EditText)v.findViewById(R.id.etNumeroExpediente));
            if(!et.getText().toString().equals("")) {
                fisController.getPatrimonio().setExpedienteActual(Integer.parseInt(et.getText().toString()));
            }

            tv = ((TextView) v.findViewById(R.id.tvTenenciaVivienda));
            if (fisController.getPatrimonio().getIdTenenciaVivienda().equals("")) {
                setError(0, tv);
                error = true;
            }

            tv = ((TextView) v.findViewById(R.id.tvAdquirioVivienda));
            if (fisController.getPatrimonio().getIdAdquirioVivienda().equals("") && (fisController.getPatrimonio().getIdTenenciaVivienda().equals("1") || fisController.getPatrimonio().getIdTenenciaVivienda().equals("2"))) {
                setError(1, tv);
                error = true;
            }

            if (fisController.getPatrimonio().getIdTenenciaVivienda().equals("2")) {
                et = ((EditText) v.findViewById(R.id.etMontoPropia));
                if(et.getText().toString().equals("")) {
                    setError(2, et);
                    error = true;
                }
                else if(Double.parseDouble(et.getText().toString()) > Double.parseDouble("999999999999")){
                    setError(20, et);
                    error = true;
                }
                else{
                    fisController.getPatrimonio().setMontoMensualPropiaHipoteca(Float.parseFloat(et.getText().toString()));
                }
            }

            if (fisController.getPatrimonio().getIdTenenciaVivienda().equals("3")) {
                et = ((EditText) v.findViewById(R.id.etMontoAlquilada));
                if (et.getText().toString().equals("")) {
                    setError(6, et);
                    error = true;
                }
                else if(Double.parseDouble(et.getText().toString()) > Double.parseDouble("999999999999")){
                    setError(20, et);
                    error = true;
                }
                else {
                    fisController.getPatrimonio().setMontoMensualAquilada(Float.parseFloat(et.getText().toString()));
                }
            }

            if (fisController.getPatrimonio().getIdTenenciaVivienda().equals("8")) {
                et = ((EditText) v.findViewById(R.id.etMontoPrecarioAlquiler));
                if (et.getText().toString().equals("")) {
                    fisController.getPatrimonio().setAlquilarPrecario(0);
                } else {
                    fisController.getPatrimonio().setAlquilarPrecario(Float.parseFloat(et.getText().toString()));
                }
            }

            if(fisController.getPatrimonio().getIdTenenciaVivienda().equals("2")) {
                TextView errorMontoPropia = (TextView) v.findViewById(R.id.errorMontoPropia);
                int selectedId = ((RadioGroup) v.findViewById(R.id.radioMontoPropia)).getCheckedRadioButtonId();
                if (selectedId == -1) {
                    if (fisController.getPatrimonio().getEstadoPropiaHpoteca().equals("")){
                        setError(5, errorMontoPropia);
                        error = true;
                    } else {
                        errorMontoPropia.setText("");
                    }
                } else {
                    String value = ((RadioButton) v.findViewById(selectedId)).getText().toString();
                    fisController.getPatrimonio().setEstadoPropiaHpoteca(value);
                    errorMontoPropia.setText("");
                }
            }

            if(fisController.getPatrimonio().getIdTenenciaVivienda().equals("3")) {

                TextView errorMontoAlquilada = (TextView) v.findViewById(R.id.errorMontoAlquilada);
                int selectedId = ((RadioGroup) v.findViewById(R.id.radioMontoAlquilada)).getCheckedRadioButtonId();
                if (selectedId == -1) {
                    if (fisController.getPatrimonio().getEstadoAlquilada().equals("")) {
                        setError(3, errorMontoAlquilada);
                        error = true;
                    } else {
                        errorMontoAlquilada.setText("");
                    }
                } else {
                    String value = ((RadioButton) v.findViewById(selectedId)).getText().toString();
                    fisController.getPatrimonio().setEstadoAlquilada(value);
                    errorMontoAlquilada.setText("");
                }
            }

            boolean[] tenenciaOtrosBienes = fisController.getPatrimonio().getTenenciaOtrosBienes();
            boolean enBlanco = true;
            for(int i=0; i< tenenciaOtrosBienes.length; i++){
                if(tenenciaOtrosBienes[i]){
                    switch(i) {
                        case 0:
                            enBlanco=false;
                            et = (EditText) v.findViewById(R.id.etAreaLote);
                            if(et.getText().toString().equals("")){
                                setError(7, et);
                                error = true;
                            }
                            else if(Double.parseDouble(et.getText().toString()) > Double.parseDouble("999999")){
                                setError(20, et);
                                error = true;
                            }
                            else{
                                fisController.getPatrimonio().setAreaM2Lote(Float.parseFloat(et.getText().toString()));
                            }
                            break;
                        case 1:
                            enBlanco=false;
                            et = (EditText) v.findViewById(R.id.etAreaConstrucciones);
                            if(et.getText().toString().equals("")){
                                setError(8, et);
                                error = true;
                            }
                            else if(Double.parseDouble(et.getText().toString()) > Double.parseDouble("999999")){
                                setError(20, et);
                                error = true;
                            }
                            else{
                                fisController.getPatrimonio().setAreaM2Construcciones1(Float.parseFloat(et.getText().toString()));
                            }
                            break;
                        case 2:
                            enBlanco=false;
                            et = (EditText) v.findViewById(R.id.etAreaFinca);
                            if(et.getText().toString().equals("")){
                                setError(9, et);
                                error = true;
                            }
                            else if(Double.parseDouble(et.getText().toString()) > Double.parseDouble("999999")){
                                setError(20, et);
                                error = true;
                            }
                            else{
                                fisController.getPatrimonio().setFincaParcelaArea(Float.parseFloat(et.getText().toString()));
                            }
                            if (fisController.getPatrimonio().getIdProduccionPara().equals("")){
                                setError(10, ((TextView) v.findViewById(R.id.tvProduccionPara)));
                                error = true;
                            }
                            break;
                        case 3:
                            enBlanco=false;
                            break;
                    }
                }
            }
            if(enBlanco){
                ((TextView) v.findViewById(R.id.tvErrorOtrosBienes)).setVisibility(View.VISIBLE);
                error = true;
            }
            else{
                ((TextView) v.findViewById(R.id.tvErrorOtrosBienes)).setVisibility(View.INVISIBLE);
            }

            boolean[] otrosBienes = fisController.getPatrimonio().getOtrosBienes();
            enBlanco=true;
            for(int i=0; i<otrosBienes.length; i++) {
                if(otrosBienes[i]){
                    enBlanco=false;
                    break;
                }
            }
            if(enBlanco){
                ((TextView) v.findViewById(R.id.tvErrorOtrosBienes2)).setVisibility(View.VISIBLE);
                error = true;
            }
            else{
                ((TextView) v.findViewById(R.id.tvErrorOtrosBienes2)).setVisibility(View.INVISIBLE);
            }

            for(int i=11; i<otrosBienes.length; i++){
                if(otrosBienes[i]){
                    switch(i) {
                        case 11:
                            et = (EditText)v.findViewById(R.id.etCantidadTV);
                            if(et.getText().toString().equals("") || et.getText().toString().equals("0")){
                                setError(18, et);
                                error = true;
                            }
                            else {
                                fisController.getPatrimonio().setCantidadTV(Integer.parseInt(et.getText().toString()));
                            }
                            break;
                        case 12:
                            et = (EditText)v.findViewById(R.id.etCantidadEquipoSonido);
                            if(et.getText().toString().equals("") || et.getText().toString().equals("0")){
                                setError(18, et);
                                error = true;
                            }
                            else {
                                fisController.getPatrimonio().setCantidadEquipoSonido(Integer.parseInt(et.getText().toString()));
                            }
                            break;
                        case 13:
                            et = (EditText)v.findViewById(R.id.etCantidadComputadoras);
                            if(et.getText().toString().equals("") || et.getText().toString().equals("0")){
                                setError(18, et);
                                error = true;
                            }
                            else {
                                fisController.getPatrimonio().setCantidadComputadoras(Integer.parseInt(et.getText().toString()));
                            }
                            break;
                        case 14:
                            et = (EditText)v.findViewById(R.id.etNumeroResidencial);
                            if(et.getText().toString().equals("")){
                                setError(11, et);
                                error = true;
                            }
                            else {
                                fisController.getPatrimonio().setTelefonoResidencial1(et.getText().toString());
                            }
                            et = (EditText)v.findViewById(R.id.etCostoResidencial);
                            if(et.getText().toString().equals("")){
                                setError(12, et);
                                error = true;
                            }
                            else if(Double.parseDouble(et.getText().toString()) > Double.parseDouble("9999999999")){
                                setError(20, et);
                                error = true;
                            }
                            else {
                                fisController.getPatrimonio().setMontoTelefonoResidencial1(Float.parseFloat(et.getText().toString()));
                            }
                            break;
                        case 15:
                            et = (EditText) v.findViewById(R.id.etNumeroCelular);
                            if(et.getText().toString().equals("")){
                                setError(11, et);
                                error = true;
                            }
                            else {
                                fisController.getPatrimonio().setTelefonoCelular1(et.getText().toString());
                            }
                            et = (EditText) v.findViewById(R.id.etCostoCelular);
                            if(et.getText().toString().equals("")){
                                setError(12, et);
                                error = true;
                            }
                            else if(Double.parseDouble(et.getText().toString()) > Double.parseDouble("999999")){
                                setError(20, et);
                                error = true;
                            }
                            else {
                                fisController.getPatrimonio().setMontoTelefonoCelular1(Float.parseFloat(et.getText().toString()));
                            }
                            et = (EditText) v.findViewById(R.id.etCantidadCelular);
                            if(et.getText().toString().equals("") || et.getText().toString().equals("0")){
                                setError(18, et);
                                error = true;
                            }
                            else {
                                fisController.getPatrimonio().setCantidadTelefonoCelular(Integer.parseInt(et.getText().toString()));
                            }
                            break;
                        case 16:
                            et = (EditText) v.findViewById(R.id.etAnoVehiculoPersonal);
                            if(et.getText().toString().equals("")){
                                setError(13, et);
                                error = true;
                            }
                            else {
                                int anno = Integer.parseInt(et.getText().toString());
                                if(anno > 1900 && anno <2050) {
                                    fisController.getPatrimonio().setAnoVehiculoPersonal(et.getText().toString());
                                }
                                else{
                                    setError(19, et);
                                    error = true;
                                }
                            }
                            et = (EditText) v.findViewById(R.id.etCantidadVehiculoPersonal);
                            if(et.getText().toString().equals("") || et.getText().toString().equals("0")){
                                setError(18, et);
                                error = true;
                            }
                            else {
                                fisController.getPatrimonio().setCantidadVehiculoPersonal(Integer.parseInt(et.getText().toString()));
                            }
                            break;
                        case 17:
                            et = (EditText) v.findViewById(R.id.etAnoVehiculoTrabajo);
                            if(et.getText().toString().equals("")){
                                setError(14, et);
                                error = true;
                            }
                            else {
                                int anno = Integer.parseInt(et.getText().toString());
                                if(anno > 1900 && anno <2050) {
                                    fisController.getPatrimonio().setAnoVehiculoTrabajo(et.getText().toString());
                                }
                                else{
                                    setError(19, et);
                                    error = true;
                                }
                            }
                            break;
                        case 18:
                            et = (EditText) v.findViewById(R.id.etAnoMotocicletaTrabajo);
                            if(et.getText().toString().equals("")){
                                setError(15, et);
                                error = true;
                            }
                            else {
                                int anno = Integer.parseInt(et.getText().toString());
                                if(anno > 1900 && anno <2050) {
                                    fisController.getPatrimonio().setAnoMotocicletaTrabajo(et.getText().toString());
                                }
                                else{
                                    setError(19, et);
                                    error = true;
                                }
                            }
                            break;
                        case 19:
                            et = (EditText) v.findViewById(R.id.etNumeroCabezasBovino);
                            if(et.getText().toString().equals("") || et.getText().toString().equals("0")){
                                setError(16, et);
                                error = true;
                            }
                            else if(Double.parseDouble(et.getText().toString()) > Double.parseDouble("10000")){
                                setError(20, et);
                                error = true;
                            }
                            else {
                                fisController.getPatrimonio().setNumeroCabezasBovino(Integer.parseInt(et.getText().toString()));
                            }
                            break;
                        case 20:
                            et = (EditText) v.findViewById(R.id.etNumeroCabezasPorcino);
                            if(et.getText().toString().equals("") || et.getText().toString().equals("0")){
                                setError(17, et);
                                error = true;
                            }
                            else if(Double.parseDouble(et.getText().toString()) > Double.parseDouble("10000")){
                                setError(20, et);
                                error = true;
                            }
                            else {
                                fisController.getPatrimonio().setNumeroCabezasPorcino(Integer.parseInt(et.getText().toString()));
                            }
                            break;
                        case 21:
                            et = (EditText) v.findViewById(R.id.etNumeroOtro);
                            if(et.getText().toString().equals("")){
                                setError(11, et);
                                error = true;
                            }
                            else {
                                fisController.getPatrimonio().setTelefonoOtro(et.getText().toString());
                            }
                            break;
                    }
                }
            }
           /*
            EditText etCorreoElectronico = ((EditText)v.findViewById(R.id.etCorreoElectronico));
            patrimonio.setCorreoElectronico(etCorreoElectronico.getText().toString());
           */
            return !error;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);

            ((Activity_Patrimonio) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }

        @Override
        public void onStart() {
            super.onStart();
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

        }
    }

}