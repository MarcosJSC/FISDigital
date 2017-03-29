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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import cr.bamboo.fisdigital.R;
import cr.bamboo.fisdigital.layout.fragment.DashboardDrawer_Fragment;
import cr.bamboo.fisdigital.layout.fragment.NavigationDrawer_Fragment;
import cr.bamboo.fisdigital.logic.bo.Integrante;
import cr.bamboo.fisdigital.logic.global.FISController;
import cr.bamboo.fisdigital.data.dbmodel.Opcion;

public class Activity_Integrante_2 extends FISActivity
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
        setContentView(R.layout.activity_integrante_2);

        mNavigationDrawerFragmentCara1 = (NavigationDrawer_Fragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);

        mDashboardDrawerFragmentCara1 = (DashboardDrawer_Fragment)
                getFragmentManager().findFragmentById(R.id.dashboard_drawer1);

        mDashboardDrawerFragmentCara1.getArguments().putString("textoSecciones", "2/10");
        mDashboardDrawerFragmentCara1.getArguments().putString("textoPreguntas", "35/61");
        mDashboardDrawerFragmentCara1.setData();

        mTitle = getString(R.string.title_seccion3);

        // Set up the drawer.
        mNavigationDrawerFragmentCara1.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout6));

        mDashboardDrawerFragmentCara1.setUp(R.id.dashboard_drawer1, (DrawerLayout) findViewById(R.id.drawer_layout6));

        FragmentManager fragmentManager = getFragmentManager();
        globalFragment =  PlaceholderFragment6.newInstance(1);
        fragmentManager.beginTransaction()
                .replace(R.id.container6, globalFragment)
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
        // update the main content by replacing fragments

        //Parentezo número
        if(catalogId == 0) {
            setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvParentescoNumero));
            fisController.getIntegrante().setParentezcoNumero(opcion);
        }

        //Parentezo jefes
        if(catalogId == 1) {
            setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvParentescoJefes));
            fisController.getIntegrante().setParentezcoJefes(opcion);
        }

        //Condicion Actividad
        if(catalogId == 2) {
            setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvCondicionActividad));
            fisController.getIntegrante().setCondicionActividad(opcion);

            if (Integer.parseInt(fisController.getIntegrante().getIdCondicionActividad()) > 3) {
                if (Integer.parseInt(fisController.getIntegrante().getIdCondicionActividad()) > 4) {
                    fisController.getIntegrante().setIdOcupacionOficio("");
                    setText("No Aplica", (TextView) globalFragment.getView().findViewById(R.id.tvOcupacionOficio));
                    ((TextView) globalFragment.getView().findViewById(R.id.btnOcupacionOficio)).setEnabled(false);
                }else{
                    fisController.getIntegrante().setIdOcupacionOficio("");
                    setText("", (TextView) globalFragment.getView().findViewById(R.id.tvOcupacionOficio));
                    ((TextView) globalFragment.getView().findViewById(R.id.btnOcupacionOficio)).setEnabled(true);
                }
                setText("No Aplica", (TextView) globalFragment.getView().findViewById(R.id.tvCategoriaOcupacional));
                ((TextView) globalFragment.getView().findViewById(R.id.btnCategoriaOcupacional)).setEnabled(false);
                ((EditText) globalFragment.getView().findViewById(R.id.etIngresoMensualSalario)).setText("0");
                ((EditText) globalFragment.getView().findViewById(R.id.etIngresoMensualSalario)).setEnabled(false);
                fisController.getIntegrante().setIngresoSalario(0);
                ((EditText) globalFragment.getView().findViewById(R.id.etIngresoMensualPropioPatrono)).setText("0");
                ((EditText) globalFragment.getView().findViewById(R.id.etIngresoMensualPropioPatrono)).setEnabled(false);
                fisController.getIntegrante().setIngresoPropio(0);
            } else {
                fisController.getIntegrante().setIdOcupacionOficio("");
                setText("", (TextView) globalFragment.getView().findViewById(R.id.tvOcupacionOficio));
                ((TextView) globalFragment.getView().findViewById(R.id.btnOcupacionOficio)).setEnabled(true);
                setText("", (TextView) globalFragment.getView().findViewById(R.id.tvCategoriaOcupacional));
                ((TextView) globalFragment.getView().findViewById(R.id.btnCategoriaOcupacional)).setEnabled(true);
                ((EditText) globalFragment.getView().findViewById(R.id.etIngresoMensualSalario)).setText("");
                ((EditText) globalFragment.getView().findViewById(R.id.etIngresoMensualSalario)).setEnabled(true);
                ((EditText) globalFragment.getView().findViewById(R.id.etIngresoMensualPropioPatrono)).setText("");
                ((EditText) globalFragment.getView().findViewById(R.id.etIngresoMensualPropioPatrono)).setEnabled(true);
            }
        }

        //Ocupacion u Oficio
        if(catalogId == 3) {
            setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvOcupacionOficio));
            fisController.getIntegrante().setOcupacionOficio(opcion);
        }

        //Categoria ocupacional
        if(catalogId == 4) {
            setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvCategoriaOcupacional));
            fisController.getIntegrante().setCategoriaOcupacional(opcion);
            if(fisController.getIntegrante().getIdCategoriaOcupacional().equals("1")){
                ((EditText) globalFragment.getView().findViewById(R.id.etIngresoMensualSalario)).setText("0");
                ((EditText) globalFragment.getView().findViewById(R.id.etIngresoMensualSalario)).setEnabled(false);
                fisController.getIntegrante().setIngresoSalario(0);
                ((EditText) globalFragment.getView().findViewById(R.id.etIngresoMensualPropioPatrono)).setText("0");
                ((EditText) globalFragment.getView().findViewById(R.id.etIngresoMensualPropioPatrono)).setEnabled(false);
                fisController.getIntegrante().setIngresoPropio(0);
            }
            else if(Integer.parseInt(fisController.getIntegrante().getIdCategoriaOcupacional()) > 1 && Integer.parseInt(fisController.getIntegrante().getIdCategoriaOcupacional()) < 5) {
                ((EditText) globalFragment.getView().findViewById(R.id.etIngresoMensualSalario)).setText("");
                ((EditText) globalFragment.getView().findViewById(R.id.etIngresoMensualSalario)).setEnabled(true);
                ((EditText) globalFragment.getView().findViewById(R.id.etIngresoMensualPropioPatrono)).setText("0");
                ((EditText) globalFragment.getView().findViewById(R.id.etIngresoMensualPropioPatrono)).setEnabled(false);
                fisController.getIntegrante().setIngresoPropio(0);
            }
            else if(Integer.parseInt(fisController.getIntegrante().getIdCategoriaOcupacional()) > 4){
                ((EditText) globalFragment.getView().findViewById(R.id.etIngresoMensualSalario)).setText("0");
                ((EditText) globalFragment.getView().findViewById(R.id.etIngresoMensualSalario)).setEnabled(false);
                ((EditText) globalFragment.getView().findViewById(R.id.etIngresoMensualPropioPatrono)).setText("");
                ((EditText) globalFragment.getView().findViewById(R.id.etIngresoMensualPropioPatrono)).setEnabled(true);
                fisController.getIntegrante().setIngresoSalario(0);
            }
            else{
                ((EditText) globalFragment.getView().findViewById(R.id.etIngresoMensualSalario)).setText("");
                ((EditText) globalFragment.getView().findViewById(R.id.etIngresoMensualSalario)).setEnabled(true);
                fisController.getIntegrante().setIngresoSalario(0);
                ((EditText) globalFragment.getView().findViewById(R.id.etIngresoMensualPropioPatrono)).setText("");
                ((EditText) globalFragment.getView().findViewById(R.id.etIngresoMensualPropioPatrono)).setEnabled(true);
                fisController.getIntegrante().setIngresoPropio(0);
                ((EditText) globalFragment.getView().findViewById(R.id.etOtrosIngresos)).setText("");
                ((EditText) globalFragment.getView().findViewById(R.id.etOtrosIngresos)).setEnabled(true);
                fisController.getIntegrante().setOtrosIngresos(0);
            }
        }
    }
    public PlaceholderFragment6 globalFragment;
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
    public static class PlaceholderFragment6 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        FISController fisController = FISController.getInstance();
        boolean esMiembro = false;

        View globalRootView;
        ScrollView sv;
        String[] errorMessages = new String[]{
                "El Parentezco Según Número de Familia no puede estar en blanco.",
                "El Parentezco Entre Jefes no puede estar en blanco.",
                "El Número de Hogar no puede estar en blanco.",
                "La Condición Actividad no puede estar en blanco.",
                "La Ocupación u Oficio no puede estar en blanco.",
                "La Categoría Ocupacional no puede estar en blanco.",
                "El Ingreso Mensual por Salario no puede ser cero.",
                "El Ingreso Mensual Cuenta Propia Patrono no puede ser cero.",
                "Otros Ingresos Mensuales no puede ser cero",
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
        public static PlaceholderFragment6 newInstance(int sectionNumber) {
            PlaceholderFragment6 fragment = new PlaceholderFragment6();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment6() {
        }

        Activity_Integrante_2 parentActivity;
        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {
            parentActivity = ((Activity_Integrante_2) getActivity());

            globalRootView= inflater.inflate(R.layout.fragment_integrante_2, container, false);
            Init(globalRootView);
            return globalRootView;
        }

        void Init(final View v){
            sv = (ScrollView)v.findViewById(R.id.svGeneralCara6);
            v.findViewById(R.id.etNumeroHogar).clearFocus();

            if (fisController.idIntegranteEditar != 0) {
                try {

                    if (fisController.getIntegrante().getJefeFamilia()) {
                        if (fisController.positionFamilia == 0) {
                            fisController.esJefePrincipal = true;
                            fisController.esJefeSecundario = false;
                        } else {
                            fisController.esJefePrincipal = false;
                            fisController.esJefeSecundario = true;
                        }
                    } else {
                        fisController.esJefePrincipal = false;
                        fisController.esJefeSecundario = false;
                    }

                    ((EditText)v.findViewById(R.id.etNumeroHogar)).setText(""+fisController.getIntegrante().getNumeroHogar());
                    ((EditText) v.findViewById(R.id.etIngresoMensualSalario)).setText(String.valueOf((fisController.getIntegrante().getIngresoSalario())));
                    ((EditText) v.findViewById(R.id.etIngresoMensualSalario)).setEnabled(true);
                    ((EditText) v.findViewById(R.id.etIngresoMensualPropioPatrono)).setText(String.valueOf(fisController.getIntegrante().getIngresoPropio()));
                    ((EditText) v.findViewById(R.id.etIngresoMensualPropioPatrono)).setEnabled(true);
                    ((EditText) v.findViewById(R.id.etOtrosIngresos)).setText(String.valueOf(fisController.getIntegrante().getOtrosIngresos()));
                    ((EditText) v.findViewById(R.id.etOtrosIngresos)).setEnabled(true);
                    ((EditText) v.findViewById(R.id.etIngresoMensualSalario)).setText(String.valueOf(fisController.getIntegrante().getIngresoSalario()));

                    if(!fisController.getIntegrante().getIdOcupacionOficio().isEmpty()) {
                        ((TextView) v.findViewById(R.id.tvOcupacionOficio)).setText(fisController.getIntegrante().getOcupacionOficio());
                        ((TextView) v.findViewById(R.id.btnOcupacionOficio)).setEnabled(true);
                    }

                    if(!fisController.getIntegrante().getIdCategoriaOcupacional().isEmpty()) {
                        ((TextView) v.findViewById(R.id.tvCategoriaOcupacional)).setText(fisController.getIntegrante().getCategoriaOcupacional());
                        ((TextView) v.findViewById(R.id.btnCategoriaOcupacional)).setEnabled(true);
                        if(fisController.getIntegrante().getIdCategoriaOcupacional().equals("1")){
                            ((EditText)v.findViewById(R.id.etIngresoMensualSalario)).setText("0");
                            ((EditText)v.findViewById(R.id.etIngresoMensualSalario)).setEnabled(false);
                            ((EditText)v.findViewById(R.id.etIngresoMensualPropioPatrono)).setText("0");
                            ((EditText)v.findViewById(R.id.etIngresoMensualPropioPatrono)).setEnabled(false);
                        }
                        else if(Integer.parseInt(fisController.getIntegrante().getIdCategoriaOcupacional()) > 1 && Integer.parseInt(fisController.getIntegrante().getIdCategoriaOcupacional()) < 5) {
                            ((EditText) v.findViewById(R.id.etIngresoMensualSalario)).setText(fisController.getIntegrante().getIngresoSalario()+"");
                            ((EditText) v.findViewById(R.id.etIngresoMensualSalario)).setEnabled(true);
                            ((EditText) v.findViewById(R.id.etIngresoMensualPropioPatrono)).setText("0");
                            ((EditText) v.findViewById(R.id.etIngresoMensualPropioPatrono)).setEnabled(false);
                            fisController.getIntegrante().setIngresoPropio(0);
                        }
                        else if(Integer.parseInt(fisController.getIntegrante().getIdCategoriaOcupacional()) > 4){
                            ((EditText) v.findViewById(R.id.etIngresoMensualSalario)).setText("0");
                            ((EditText) v.findViewById(R.id.etIngresoMensualSalario)).setEnabled(false);
                            ((EditText) v.findViewById(R.id.etIngresoMensualPropioPatrono)).setText(fisController.getIntegrante().getIngresoPropio()+"");
                            ((EditText) v.findViewById(R.id.etIngresoMensualPropioPatrono)).setEnabled(true);
                            fisController.getIntegrante().setIngresoSalario(0);
                        }
                    }

                    if (!fisController.getIntegrante().getIdCondicionActividad().isEmpty()) {
                        ((TextView) v.findViewById(R.id.tvCondicionActividad)).setText(fisController.getIntegrante().getCondicionActividad());
                        ((TextView) v.findViewById(R.id.btnCondicionActividad)).setEnabled(true);
                        if (Integer.parseInt(fisController.getIntegrante().getIdCondicionActividad()) > 3) {
                            if (Integer.parseInt(fisController.getIntegrante().getIdCondicionActividad()) > 4) {
                                ((TextView) v.findViewById(R.id.tvOcupacionOficio)).setText("No Aplica");
                                ((TextView) v.findViewById(R.id.btnOcupacionOficio)).setEnabled(false);
                            }
                            ((TextView) v.findViewById(R.id.tvCategoriaOcupacional)).setText("No Aplica");
                            ((TextView) v.findViewById(R.id.btnCategoriaOcupacional)).setEnabled(false);
                            ((EditText) v.findViewById(R.id.etIngresoMensualSalario)).setText("0");
                            ((EditText) v.findViewById(R.id.etIngresoMensualSalario)).setEnabled(false);
                            ((EditText) v.findViewById(R.id.etIngresoMensualPropioPatrono)).setText("0");
                            ((EditText) v.findViewById(R.id.etIngresoMensualPropioPatrono)).setEnabled(false);
                        }
                    }



                        if(fisController.getIntegrante().getEdad() < 5){
                            ((TextView)v.findViewById(R.id.tvCondicionActividad)).setText("No aplica.");
                            ((Button)v.findViewById(R.id.btnCondicionActividad)).setEnabled(false);
                            ((TextView) v.findViewById(R.id.tvOcupacionOficio)).setText("No aplica.");
                            ((TextView) v.findViewById(R.id.btnOcupacionOficio)).setEnabled(false);
                            ((TextView) v.findViewById(R.id.tvCategoriaOcupacional)).setText("No aplica.");
                            ((TextView) v.findViewById(R.id.btnCategoriaOcupacional)).setEnabled(false);
                            ((TextView) v.findViewById(R.id.etIngresoMensualSalario)).setText("0");
                            ((TextView) v.findViewById(R.id.etIngresoMensualSalario)).setEnabled(false);
                            ((TextView) v.findViewById(R.id.etIngresoMensualPropioPatrono)).setText("0");
                            ((TextView) v.findViewById(R.id.etIngresoMensualPropioPatrono)).setEnabled(false);
                        }

                    if(fisController.esJefePrincipal) {

                        v.findViewById(R.id.btnParentescoNumero).setEnabled(false);
                        ((TextView) v.findViewById(R.id.tvParentescoNumero)).setText("Jefe (a)");
                        fisController.getIntegrante().setIdParentezcoNumero("1");

                        v.findViewById(R.id.btnParentescoJefes).setEnabled(false);
                        ((TextView) v.findViewById(R.id.tvParentescoJefes)).setText("Jefe (a)");
                        fisController.getIntegrante().setIdParentezcoJefes("1");

                        ((EditText)v.findViewById(R.id.etNumeroHogar)).setText("1");
                        ((EditText)v.findViewById(R.id.etNumeroHogar)).setEnabled(false);
                    }
                    else{
                        if(fisController.esJefeSecundario){
                            v.findViewById(R.id.btnParentescoNumero).setEnabled(false);
                            ((TextView) v.findViewById(R.id.tvParentescoNumero)).setText("Jefe (a)");
                            fisController.getIntegrante().setIdParentezcoNumero("1");

                            v.findViewById(R.id.btnParentescoJefes).setEnabled(true);
                            ((TextView) v.findViewById(R.id.tvParentescoJefes)).setText(fisController.getIntegrante().getParentezcoJefes());
                        }
                        else{
                            v.findViewById(R.id.btnParentescoNumero).setEnabled(true);
                            ((TextView) v.findViewById(R.id.tvParentescoNumero)).setText(fisController.getIntegrante().getParentezcoNumero());

                            v.findViewById(R.id.btnParentescoJefes).setEnabled(false);
                            ((TextView) v.findViewById(R.id.tvParentescoJefes)).setText("No Aplica.");
                        }
                        esMiembro = true;
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            else{


                if(fisController.getIntegrante().getEdad() < 5){
                    ((TextView)v.findViewById(R.id.tvCondicionActividad)).setText("No aplica.");
                    ((Button)v.findViewById(R.id.btnCondicionActividad)).setEnabled(false);
                    ((TextView) v.findViewById(R.id.tvOcupacionOficio)).setText("No aplica.");
                    ((TextView) v.findViewById(R.id.btnOcupacionOficio)).setEnabled(false);
                    ((TextView) v.findViewById(R.id.tvCategoriaOcupacional)).setText("No aplica.");
                    ((TextView) v.findViewById(R.id.btnCategoriaOcupacional)).setEnabled(false);
                    ((TextView) v.findViewById(R.id.etIngresoMensualSalario)).setText("0");
                    ((TextView) v.findViewById(R.id.etIngresoMensualSalario)).setEnabled(false);
                    ((TextView) v.findViewById(R.id.etIngresoMensualPropioPatrono)).setText("0");
                    ((TextView) v.findViewById(R.id.etIngresoMensualPropioPatrono)).setEnabled(false);
                }


                if(fisController.esJefePrincipal) {

                    v.findViewById(R.id.btnParentescoNumero).setEnabled(false);
                    ((TextView) v.findViewById(R.id.tvParentescoNumero)).setText("Jefe (a)");
                    fisController.getIntegrante().setIdParentezcoNumero("1");

                    v.findViewById(R.id.btnParentescoJefes).setEnabled(false);
                    ((TextView) v.findViewById(R.id.tvParentescoJefes)).setText("Jefe (a)");
                    fisController.getIntegrante().setIdParentezcoJefes("1");

                    ((EditText)v.findViewById(R.id.etNumeroHogar)).setText("1");
                    ((EditText)v.findViewById(R.id.etNumeroHogar)).setEnabled(false);

                    fisController.getIntegrante().setJefeFamilia(true);

                }
                else{
                    if(fisController.esJefeSecundario){
                        v.findViewById(R.id.btnParentescoNumero).setEnabled(false);
                        ((TextView) v.findViewById(R.id.tvParentescoNumero)).setText("Jefe (a)");
                        fisController.getIntegrante().setIdParentezcoNumero("1");

                        v.findViewById(R.id.btnParentescoJefes).setEnabled(true);
                        ((TextView) v.findViewById(R.id.tvParentescoJefes)).setText("");
                        fisController.getIntegrante().setIdParentezcoJefes("");
                    }
                    else{
                        v.findViewById(R.id.btnParentescoJefes).setEnabled(false);
                        ((TextView) v.findViewById(R.id.tvParentescoJefes)).setText("No Aplica.");
                        fisController.getIntegrante().setIdParentezcoJefes("");
                    }
                    esMiembro = true;
                    fisController.getIntegrante().setJefeFamilia(false);
                }

            }


            // One for each button/dropdownlist
            v.findViewById(R.id.btnParentescoNumero).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_Integrante_2 localAct = ((Activity_Integrante_2) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(Integrante.getOpcionesParentezcoNumero(esMiembro), 0);
                    parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};
                }
            });
////
            v.findViewById(R.id.btnParentescoJefes).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_Integrante_2 localAct = ((Activity_Integrante_2) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(Integrante.getOpcionesParentezcoJefes(esMiembro), 1);
                    parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};
                }
            });
//
            v.findViewById(R.id.btnCondicionActividad).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_Integrante_2 localAct = ((Activity_Integrante_2) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(Integrante.getOpcionesCondicionActividad(), 2);
                    parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};
                }
            });

            v.findViewById(R.id.btnOcupacionOficio).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_Integrante_2 localAct = ((Activity_Integrante_2) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(3);
                    parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};
                }
            });

            v.findViewById(R.id.btnCategoriaOcupacional).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_Integrante_2 localAct = ((Activity_Integrante_2) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(Integrante.getOpcionesCategoriaOcupacional(), 4);
                    parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};
                }
            });
//
            v.findViewById(R.id.btnSiguiente).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {

                    ((LinearLayout)v.findViewById(R.id.errorMsg6)).requestFocus();

                    if(!formIsValid(v)){
                        ScrollView sv = (ScrollView)v.findViewById(R.id.svGeneralCara6);
                        sv.scrollTo(0, 0);
                        ((LinearLayout)v.findViewById(R.id.errorMsg6)).setVisibility(View.VISIBLE);
                    }
                    else {
                        parentActivity.mDashboardDrawerFragmentCara1.saveObservaciones();
                        ((LinearLayout)v.findViewById(R.id.errorMsg6)).setVisibility(View.GONE);
                        Intent openStep = new Intent(parentActivity, Activity_Integrante_3.class);
                        startActivity(openStep);
                    }
                }
            });



            sv.post(new Runnable(){
                public void run(){
                    sv.scrollTo(parentActivity.memoryScrollPos[0], parentActivity.memoryScrollPos[1]);
                }
            });

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
            TextView tv = null;
            EditText et = null;

            tv = ((TextView) v.findViewById(R.id.tvParentescoNumero));
            if (fisController.getIntegrante().getIdParentezcoNumero().equals("")) {
                setError(0, tv);
                error = true;
            }

            tv = ((TextView) v.findViewById(R.id.tvParentescoJefes));
            if (fisController.getIntegrante().getIdParentezcoJefes().equals("") && fisController.getIntegrante().getJefeFamilia()) {
                setError(1, tv);
                error = true;
            }

            et = ((EditText) v.findViewById(R.id.etNumeroHogar));
            if (et.getText().toString().trim().equals("")) {
                setError(2, et);
                error = true;
            } else {
                fisController.getIntegrante().setNumeroHogar(Integer.parseInt(et.getText().toString()));
            }

            tv = ((TextView) v.findViewById(R.id.tvCondicionActividad));
            if (fisController.getIntegrante().getIdCondicionActividad().equals("") && fisController.getIntegrante().getEdad() > 5) {
                setError(3, tv);
                error = true;
            }

            tv = ((TextView) v.findViewById(R.id.tvOcupacionOficio));
            if (fisController.getIntegrante().getIdOcupacionOficio().equals("") && fisController.getIntegrante().getEdad() > 5) {
                if (!fisController.getIntegrante().getIdCondicionActividad().equals("") && Integer.parseInt(fisController.getIntegrante().getIdCondicionActividad()) < 3) {
                    setError(4, tv);
                    error = true;
                }
            }

            tv = ((TextView) v.findViewById(R.id.tvCategoriaOcupacional));
            if (fisController.getIntegrante().getIdCategoriaOcupacional().equals("") && fisController.getIntegrante().getEdad() > 5) {
                if (!fisController.getIntegrante().getIdCondicionActividad().equals("") && Integer.parseInt(fisController.getIntegrante().getIdCondicionActividad()) < 3) {
                    setError(5, tv);
                    error = true;
                }
            }

            et = ((EditText) v.findViewById(R.id.etIngresoMensualSalario));
            if (!fisController.getIntegrante().getIdCategoriaOcupacional().isEmpty()) {
                int catOcupacional = Integer.parseInt(fisController.getIntegrante().getIdCategoriaOcupacional());
                if (et.getText().toString().trim().equals("") && (catOcupacional > 1 && catOcupacional < 5)) {
                    setError(6, et);
                    error = true;
                } else {
                    et.setError(null);
                    if(et.getText().toString().trim().equals("")){
                        fisController.getIntegrante().setIngresoSalario(0);
                    }
                    else if(Double.parseDouble(et.getText().toString()) > 3500000){
                        setError(9, et);
                        error = true;
                    }
                    else {
                        fisController.getIntegrante().setIngresoSalario(Float.parseFloat(et.getText().toString()));
                    }
                }
            }
            else {
                fisController.getIntegrante().setIngresoSalario(0);
            }

            et = ((EditText) v.findViewById(R.id.etIngresoMensualPropioPatrono));
            if (et.getText().toString().trim().equals("")) {
                if (!fisController.getIntegrante().getIdCategoriaOcupacional().isEmpty()) {
                    int catOcupacional = Integer.parseInt(fisController.getIntegrante().getIdCategoriaOcupacional());
                    if (catOcupacional > 4) {
                        setError(7, et);
                        error = true;
                    }
                } else {
                    fisController.getIntegrante().setIngresoPropio(0);
                }
            }
            else if(Double.parseDouble(et.getText().toString()) > 3500000){
                setError(9, et);
                error = true;
            }
            else {
                et.setError(null);
                fisController.getIntegrante().setIngresoPropio(Float.parseFloat(et.getText().toString()));
            }


            et = ((EditText) v.findViewById(R.id.etOtrosIngresos));
            if (et.getText().toString().trim().equals("")) {
                fisController.getIntegrante().setOtrosIngresos(0);
            }
            else if(Double.parseDouble(et.getText().toString()) > 3500000){
                setError(9, et);
                error = true;
            }
            else {
                fisController.getIntegrante().setOtrosIngresos(Float.parseFloat(et.getText().toString()));
            }
            return !error;
        }


        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);

            ((Activity_Integrante_2) activity).onSectionAttached(
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