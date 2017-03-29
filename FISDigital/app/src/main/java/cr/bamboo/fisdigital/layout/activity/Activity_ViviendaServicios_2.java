package cr.bamboo.fisdigital.layout.activity;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridLayout;import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import cr.bamboo.fisdigital.R;
import cr.bamboo.fisdigital.layout.fragment.DashboardDrawer_Fragment;
import cr.bamboo.fisdigital.layout.fragment.NavigationDrawer_Fragment;
import cr.bamboo.fisdigital.logic.bo.ViviendaServicios;
import cr.bamboo.fisdigital.logic.global.FISController;
import cr.bamboo.fisdigital.data.dbmodel.Opcion;

public class Activity_ViviendaServicios_2 extends FISActivity
        implements NavigationDrawer_Fragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawer_Fragment mNavigationDrawerFragmentCara1;
    private DashboardDrawer_Fragment mDashboardDrawerFragmentCara1;

    private FISController fisController = FISController.getInstance();
    
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vivienda_servicios_2);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mNavigationDrawerFragmentCara1 = (NavigationDrawer_Fragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);


        mDashboardDrawerFragmentCara1 = (DashboardDrawer_Fragment)
                getFragmentManager().findFragmentById(R.id.dashboard_drawer1);

        mDashboardDrawerFragmentCara1.getArguments().putString("textoSecciones", "1/10");
        mDashboardDrawerFragmentCara1.getArguments().putString("textoPreguntas", "18/61");
        mDashboardDrawerFragmentCara1.setData();

        mTitle = getString(R.string.title_cara2);

        // Set up the drawer.
        mNavigationDrawerFragmentCara1.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout3));

        mDashboardDrawerFragmentCara1.setUp(R.id.dashboard_drawer1, (DrawerLayout) findViewById(R.id.drawer_layout3));

        FragmentManager fragmentManager = getFragmentManager();
        globalFragment =  PlaceholderFragment3.newInstance(1);
        fragmentManager.beginTransaction()
                .replace(R.id.container3, globalFragment)
                .commit();

        mNavigationDrawerFragmentCara1.setRetainInstance(true);
    }

    @Override
    public void onNavigationDrawerItemSelected(Opcion opcion, int catalogId) {

        //Fuente Abastecimiento Agua
        if (catalogId == 0) {
            setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvFuenteAbastecimientoAgua));
            fisController.getViviendaServicios().setFuenteAbastecimientoAgua(opcion);
            if(opcion.getCodigo().equals("1")) {
                ((GridLayout) globalFragment.getView().findViewById(R.id.gridNumeroNIS)).setVisibility(View.VISIBLE);
                ((GridLayout) globalFragment.getView().findViewById(R.id.gridMontoAgua)).setVisibility(View.VISIBLE);
            }
            else if(Integer.parseInt(opcion.getCodigo()) > 1 && Integer.parseInt(opcion.getCodigo()) <= 3) {
                ((GridLayout) globalFragment.getView().findViewById(R.id.gridMontoAgua)).setVisibility(View.VISIBLE);
                ((GridLayout) globalFragment.getView().findViewById(R.id.gridNumeroNIS)).setVisibility(View.GONE);
            }
            else{
                ((GridLayout) globalFragment.getView().findViewById(R.id.gridMontoAgua)).setVisibility(View.GONE);
                ((GridLayout) globalFragment.getView().findViewById(R.id.gridNumeroNIS)).setVisibility(View.GONE);
            }

            ((EditText) globalFragment.getView().findViewById(R.id.etMontoMensualAgua)).setText("");
            ((EditText) globalFragment.getView().findViewById(R.id.etNumeroNIS)).setText("");
        }

        //Medio Abastecimiento Agua
        if (catalogId == 1) {
            setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvMedioAbastecimientoAgua));
            fisController.getViviendaServicios().setMedioAbastecimientoAgua(opcion);
        }

        //Disponibilidad Luz Electrica
        if (catalogId == 2) {
            setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvDisponiblidadLuzElectrica));
            fisController.getViviendaServicios().setDisponibilidadLuzElectrica(opcion);

            if(opcion.getCodigo().equals("1")) {
                ((GridLayout) globalFragment.getView().findViewById(R.id.gridMontoLuz)).setVisibility(View.VISIBLE);
            }
            else{
                ((GridLayout) globalFragment.getView().findViewById(R.id.gridMontoLuz)).setVisibility(View.GONE);
            }
            ((EditText) globalFragment.getView().findViewById(R.id.etMontoMensualLuz)).setText("");
        }

        //Disponibilidad de bano
        if (catalogId == 3) {
            setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvDisponibilidadBano));
            fisController.getViviendaServicios().setDisponibilidadBano(opcion);
        }

        //Sanitario Conectado
        if (catalogId == 4) {
            setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvSanitarioConectado));
            fisController.getViviendaServicios().setSanitarioConectado(opcion);
        }

        //Fuente Energia Cocina
        if (catalogId == 5) {
            setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvFuenteEnergiaCocina));
            fisController.getViviendaServicios().setFuenteEnergiaCocina(opcion);
        }

        //Medio Eliminacion Basura
        if (catalogId == 6) {
            setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvMedioEliminacionBasura));
            fisController.getViviendaServicios().setMedioEliminacionBasura(opcion);
        }

    }

    private void setText(String description, TextView tv){
        tv.setText(description);
        tv.setTextAppearance(globalFragment.getView().getContext(), R.style.FISTextView);
        tv.setBackgroundResource(R.drawable.textview_border);
    }


    public PlaceholderFragment3 globalFragment;
    @NonNull
    @Override
    public FragmentManager getFragmentManager() {
        return super.getFragmentManager();
    }


    public int currentItem = 0;
    public boolean alreadyValidated = false;
    public int[] memoryScrollPos= new int[]{0,0};

    public void onSectionAttached(int number) {
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
    public static class PlaceholderFragment3 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        View globalRootView;
        ScrollView sv;
        String[] errorMessages = new String[]{
                "No ha indicado el número de camas.",
                "No ha seleccionado ningún valor válido para la variable Fuente de Abastecimiento de Agua.",
                "No ha indicado ningún valor para la variable Monto Mensual de Agua.",
                "No ha seleccionado ningún valor válido para la variable  Medio de Abastecimiento de Agua.",
                "No ha seleccionado ningún valor válido para la variable Disponibilidad de Luz Eléctrica.",
                "No ha indicado Monto Mensual de Luz",
                "No ha seleccionado ningún valor válido para la disponibilidad de baño.",
                "No ha seleccionado ningún valor válido para la donde esta conectado el sanitario.",
                "No ha seleccionado ningún valor válido para la Fuente de Energía de la Cocina",
                "No ha seleccionado ningún valor válido para la Medio de Eliminación de la Basura",
                "No ha ingresado el Número NIS",
                "El número de camas debe estar entre 0 y 10"

        };

        // To validate in there are errors in the form
        boolean areErrorsInForm = false;

        FISController fisController = FISController.getInstance();

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        //NavigationDrawer_Fragment parentNavigationDrawerFragment;

        //public static PlaceholderFragment newInstance(int sectionNumber, NavigationDrawer_Fragment mNavigationDrawerFragment) {
        public static PlaceholderFragment3 newInstance(int sectionNumber) {
            PlaceholderFragment3 fragment = new PlaceholderFragment3();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment3() {
        }

        Activity_ViviendaServicios_2 parentActivity;
        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {
            parentActivity = ((Activity_ViviendaServicios_2) getActivity());

            globalRootView= inflater.inflate(R.layout.fragment_vivienda_servicios_2, container, false);
            Init(globalRootView);
            return globalRootView;
        }

        void Init(final View v){
            sv = (ScrollView)v.findViewById(R.id.svGeneralCara3);

            if (fisController.idFISEditar != 0) {
                try {

                    if (fisController.getViviendaServicios().getNumeroCamas() >= 0) {
                        ((EditText) v.findViewById(R.id.etNumeroCamas)).setText("" + fisController.getViviendaServicios().getNumeroCamas());
                    }
                    if (!fisController.getViviendaServicios().getIdFuenteAbastecimientoAgua().isEmpty()) {
                        ((TextView) v.findViewById(R.id.tvFuenteAbastecimientoAgua)).setText(fisController.getViviendaServicios().getFuenteAbastecimientoAgua());

                        String idFuenteAgua = fisController.getViviendaServicios().getIdFuenteAbastecimientoAgua();
                        if (idFuenteAgua.equals("1")) {
                            ((GridLayout) v.findViewById(R.id.gridNumeroNIS)).setVisibility(View.VISIBLE);
                            ((GridLayout) v.findViewById(R.id.gridMontoAgua)).setVisibility(View.VISIBLE);
                        } else if (Integer.parseInt(idFuenteAgua) > 1 && Integer.parseInt(idFuenteAgua) <= 3) {
                            ((GridLayout) v.findViewById(R.id.gridMontoAgua)).setVisibility(View.VISIBLE);
                            ((GridLayout) v.findViewById(R.id.gridNumeroNIS)).setVisibility(View.GONE);
                        } else {
                            ((GridLayout) v.findViewById(R.id.gridMontoAgua)).setVisibility(View.GONE);
                            ((GridLayout) v.findViewById(R.id.gridNumeroNIS)).setVisibility(View.GONE);
                        }

                        if (!fisController.getViviendaServicios().getMontoMensualAgua().isEmpty()) {
                            ((EditText) v.findViewById(R.id.etMontoMensualAgua)).setText(fisController.getViviendaServicios().getMontoMensualAgua());
                        }
                        if (!fisController.getViviendaServicios().getNumeroNIS().isEmpty()) {
                            ((EditText) v.findViewById(R.id.etNumeroNIS)).setText(fisController.getViviendaServicios().getNumeroNIS());
                        }
                    }
                    if (!fisController.getViviendaServicios().getIdMedioAbastecimientoAgua().isEmpty()) {
                        ((TextView) v.findViewById(R.id.tvMedioAbastecimientoAgua)).setText(fisController.getViviendaServicios().getMedioAbastecimientoAgua());
                    }
                    if (!fisController.getViviendaServicios().getIdDisponibilidadLuzElectrica().isEmpty()) {
                        ((TextView) v.findViewById(R.id.tvDisponiblidadLuzElectrica)).setText(fisController.getViviendaServicios().getDisponibilidadLuzElectrica());

                        if (fisController.getViviendaServicios().getIdDisponibilidadLuzElectrica().equals("1")) {
                            ((GridLayout) v.findViewById(R.id.gridMontoLuz)).setVisibility(View.VISIBLE);
                        } else {
                            ((GridLayout) v.findViewById(R.id.gridMontoLuz)).setVisibility(View.GONE);
                        }

                        if (!fisController.getViviendaServicios().getMontoMensualLuz().isEmpty()) {
                            ((EditText) v.findViewById(R.id.etMontoMensualLuz)).setText(fisController.getViviendaServicios().getMontoMensualLuz());
                        }
                    }
                    if (!fisController.getViviendaServicios().getIdDisponibilidadBano().isEmpty()) {
                        ((TextView) v.findViewById(R.id.tvDisponibilidadBano)).setText(fisController.getViviendaServicios().getDisponibilidadBano());
                    }
                    if (!fisController.getViviendaServicios().getIdSanitarioConectado().isEmpty()) {
                        ((TextView) v.findViewById(R.id.tvSanitarioConectado)).setText(fisController.getViviendaServicios().getSanitarioConectado());
                    }
                    if (!fisController.getViviendaServicios().getIdFuenteEnergiaCocina().isEmpty()) {
                        ((TextView) v.findViewById(R.id.tvFuenteEnergiaCocina)).setText(fisController.getViviendaServicios().getFuenteEnergiaCocina());
                    }
                    if (!fisController.getViviendaServicios().getIdMedioEliminacionBasura().isEmpty()) {
                        ((TextView) v.findViewById(R.id.tvMedioEliminacionBasura)).setText(fisController.getViviendaServicios().getMedioEliminacionBasura());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            //decimales
//            EditText etMontoAgua = (EditText)v.findViewById(R.id.etMontoMensualAgua);
//            etMontoAgua.addTextChangedListener(new NumberFormat_TextWatcher(etMontoAgua));

            v.findViewById(R.id.btnFuenteAbastecimientoAgua).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_ViviendaServicios_2 localAct = ((Activity_ViviendaServicios_2) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(ViviendaServicios.getOpcionesFuenteAbastecimientoAgua(), 0);
                    parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};

                }
            });

            v.findViewById(R.id.btnMedioAbastecimientoAgua).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_ViviendaServicios_2 localAct = ((Activity_ViviendaServicios_2) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(ViviendaServicios.getOpcionesMedioAbastecimientoAgua(), 1);
                    parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};

                }
            });

            v.findViewById(R.id.btnDisponibilidadLuzElectrica).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_ViviendaServicios_2 localAct = ((Activity_ViviendaServicios_2) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(ViviendaServicios.getOpcionesLuzEelectrica(), 2);
                    parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};

                }
            });

            v.findViewById(R.id.btnDisponibilidadBano).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_ViviendaServicios_2 localAct = ((Activity_ViviendaServicios_2) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(ViviendaServicios.getOpcionesDisponibilidadBano(), 3);
                    parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};

                }
            });

            v.findViewById(R.id.btnSanitarioConectado).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_ViviendaServicios_2 localAct = ((Activity_ViviendaServicios_2) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(ViviendaServicios.getOpcionesSanitarioConectado(), 4);
                    parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};

                }
            });

            v.findViewById(R.id.btnFuenteEnergiaCocina).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_ViviendaServicios_2 localAct = ((Activity_ViviendaServicios_2) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(ViviendaServicios.getOpcionesFuenteEnergiaCocina(fisController.getViviendaServicios()), 5);
                    parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};

                }
            });

            v.findViewById(R.id.btnMedioEliminacionBasura).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_ViviendaServicios_2 localAct = ((Activity_ViviendaServicios_2) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(ViviendaServicios.getOpcionesMedioEliminacionBasura(), 6);
                    parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};

                }
            });

            v.findViewById(R.id.btnSiguiente).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {

                    viewFragment = v;
                    if (!formIsValid(v)) {
                        ScrollView sv = (ScrollView) v.findViewById(R.id.svGeneralCara3);
                        sv.scrollTo(0, 0);
                        ((LinearLayout) v.findViewById(R.id.errorMsg3)).setVisibility(View.VISIBLE);
                    } else {
                        parentActivity.mDashboardDrawerFragmentCara1.saveObservaciones();
                        fisController.saveViviendaServicios();
                        Intent openStep = new Intent(parentActivity, Activity_ListarFamilias.class);
                        startActivity(openStep);
                    }
                }
            });
//
//
            sv.post(new Runnable(){
                public void run(){
                    sv.scrollTo(parentActivity.memoryScrollPos[0], parentActivity.memoryScrollPos[1]);
                }
            });

        }

        View viewFragment;
        boolean formIsValid(View v) {
            v = globalRootView;
            boolean error = false;

            TextView tv = null;
            EditText et = null;

            tv = ((TextView) v.findViewById(R.id.tvFuenteAbastecimientoAgua));
            if (fisController.getViviendaServicios().getIdFuenteAbastecimientoAgua().equals("")) {
                setError(1, tv);
                error = true;
            }

            tv = ((TextView) v.findViewById(R.id.tvMedioAbastecimientoAgua ));
            if (fisController.getViviendaServicios().getIdMedioAbastecimientoAgua().equals("")) {
                setError(3, tv);
                error = true;
            }

            tv = ((TextView) v.findViewById(R.id.tvDisponiblidadLuzElectrica));
            if (fisController.getViviendaServicios().getIdDisponibilidadLuzElectrica().equals("")) {
                setError(4, tv);
                error = true;
            }

            tv = ((TextView) v.findViewById(R.id.tvDisponibilidadBano));
            if (fisController.getViviendaServicios().getIdDisponibilidadBano().equals("")) {
                setError(6, tv);
                error = true;
            }

            tv = ((TextView) v.findViewById(R.id.tvSanitarioConectado));
            if (fisController.getViviendaServicios().getIdSanitarioConectado().equals("")) {
                setError(7, tv);
                error = true;
            }

            tv = ((TextView) v.findViewById(R.id.tvFuenteEnergiaCocina));
            if (fisController.getViviendaServicios().getIdFuenteEnergiaCocina().equals("")) {
                setError(8, tv);
                error = true;
            }

            tv = ((TextView) v.findViewById(R.id.tvMedioEliminacionBasura));
            if (fisController.getViviendaServicios().getIdMedioEliminacionBasura().equals("")) {
                setError(9, tv);
                error = true;
            }

            et =(EditText)v.findViewById(R.id.etNumeroCamas);
            if(et.getText().toString().trim().equals("") ){
                setError(0,et);
                error=true;
            }
            else{
                int numeroCamas = Integer.parseInt(et.getText().toString().trim());
                if(numeroCamas>=0 &&  numeroCamas<=10) {
                    fisController.getViviendaServicios().setNumeroCamas(numeroCamas);
                }
                else{
                    setError(11,et);
                    error=true;
                }
            }

            et =(EditText)v.findViewById(R.id.etMontoMensualAgua);
            fisController.getViviendaServicios().setMontoMensualAgua(et.getText().toString());

            et =(EditText)v.findViewById(R.id.etMontoMensualLuz);
            fisController.getViviendaServicios().setMontoMensualLuz(et.getText().toString());

            et =(EditText)v.findViewById(R.id.etNumeroNIS);
            fisController.getViviendaServicios().setNumeroNIS(et.getText().toString());

            return !error;
        }

                void setError(int idErrorMsg, TextView component) {
                    component.setText(errorMessages[idErrorMsg]);
                    component.setTextAppearance(globalRootView.getContext(), R.style.FISTextViewError);
                    component.setBackgroundResource(R.drawable.textview_border_error);
                }

                void setError(int idErrorMsg, EditText component) {
                    component.setError(errorMessages[idErrorMsg]);
                }


        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);

            ((Activity_ViviendaServicios_2) activity).onSectionAttached(
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