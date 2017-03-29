package cr.bamboo.fisdigital.layout.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Calendar;

import cr.bamboo.fisdigital.R;
import cr.bamboo.fisdigital.layout.fragment.DashboardDrawer_Fragment;
import cr.bamboo.fisdigital.layout.fragment.DatePicker_Fragment;
import cr.bamboo.fisdigital.layout.fragment.NavigationDrawer_Fragment;
import cr.bamboo.fisdigital.logic.bo.Integrante;
import cr.bamboo.fisdigital.logic.global.FISController;
import cr.bamboo.fisdigital.data.dbmodel.Opcion;

public class Activity_Integrante_1 extends FragmentActivity
        implements NavigationDrawer_Fragment.NavigationDrawerCallbacks, DatePickerDialog.OnDateSetListener {

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
    Calendar goBack = null;
    @Override
    public void onBackPressed() {
        if(mDashboardDrawerFragmentCara1.isDrawerOpen()){

            mDashboardDrawerFragmentCara1.closeDrawer();
        }

        else if(mNavigationDrawerFragmentCara1.isDrawerOpen()){
            mNavigationDrawerFragmentCara1.closeDrawer();
        }
        else {
            super.onBackPressed();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integrante_1);

        if (fisController.idIntegranteEditar != 0) {
            try {
                fisController.loadIntegrante();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else{
            fisController.initIntegrante();
        }

        mNavigationDrawerFragmentCara1 = (NavigationDrawer_Fragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);

        mDashboardDrawerFragmentCara1 = (DashboardDrawer_Fragment)
                getFragmentManager().findFragmentById(R.id.dashboard_drawer1);

        mDashboardDrawerFragmentCara1.getArguments().putString("textoSecciones", "2/10");
        mDashboardDrawerFragmentCara1.getArguments().putString("textoPreguntas", "25/61");
        mDashboardDrawerFragmentCara1.setData();

        mTitle = getString(R.string.title_seccion3);

        mNavigationDrawerFragmentCara1.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout5));

        mDashboardDrawerFragmentCara1.setUp(R.id.dashboard_drawer1, (DrawerLayout) findViewById(R.id.drawer_layout5));

        FragmentManager fragmentManager = getFragmentManager();
        globalFragment =  PlaceholderFragment5.newInstance(1);
        fragmentManager.beginTransaction()
                .replace(R.id.container5, globalFragment)
                .commit();


        mNavigationDrawerFragmentCara1.setRetainInstance(true);
    }

    private void setText(String description, TextView tv){
        tv.setText(description);
        tv.setTextAppearance(globalFragment.getView().getContext(), R.style.FISTextView);
        tv.setBackgroundResource(R.drawable.textview_border);
    }

    private void setText(String description, EditText et){
        et.setText(description);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        final Calendar c = Calendar.getInstance();
        c.set(year, month, day);

        if(globalFragment != null && globalFragment.getView() !=null) {

            TextView tvFechaNacimiento = ((TextView) globalFragment.getView().findViewById(R.id.tvFechaNacimiento));

            if (Calendar.getInstance().after(c)) {

                setText(day + "/" + (month + 1) + "/" + year, tvFechaNacimiento);
                fisController.getIntegrante().setFechaNacimiento(tvFechaNacimiento.getText().toString());

                EditText etEdad = (EditText) globalFragment.getView().findViewById(R.id.etEdad);
                fisController.getIntegrante().setEdad(Integrante.getEdadPorFecha(fisController.getIntegrante().getFechaNacimiento()));
                setText(String.valueOf(fisController.getIntegrante().getEdad()), etEdad);

                ((TextView) globalFragment.getView().findViewById(R.id.tvErrorFechaNacimiento)).setVisibility(View.GONE);


                if (fisController.getIntegrante().getEdad() < 12) {
                    TextView estadoCivil = ((TextView) globalFragment.getView().findViewById(R.id.tvEstadoCivil));
                    Button btnEstadoCivil = ((Button) globalFragment.getView().findViewById(R.id.btnEstadoCivil));
                    estadoCivil.setText("No aplica");
                    btnEstadoCivil.setEnabled(false);
                    fisController.getIntegrante().setIdEstadoCivil("");
                } else {
                    TextView estadoCivil = ((TextView) globalFragment.getView().findViewById(R.id.tvEstadoCivil));
                    Button btnEstadoCivil = ((Button) globalFragment.getView().findViewById(R.id.btnEstadoCivil));
                    btnEstadoCivil.setEnabled(true);
                    if (fisController.getIntegrante().getIdEstadoCivil().equals("")) {
                        estadoCivil.setText("");
                    } else {
                        estadoCivil.setText(fisController.getIntegrante().getEstadoCivil());
                    }
                }
            } else {
                ((TextView) globalFragment.getView().findViewById(R.id.tvErrorFechaNacimiento)).setVisibility(View.VISIBLE);
                setError(((TextView) globalFragment.getView().findViewById(R.id.tvErrorFechaNacimiento)), "Debe seleccionar una fecha menor al día actual");
            }
        }
    }

    void setError(TextView component, String error) {
        component.setText(error);
        component.setTextAppearance(this.getApplicationContext(), R.style.FISTextViewError);
        component.setBackgroundResource(R.drawable.textview_border_error);
    }

    @Override
    public void onNavigationDrawerItemSelected(Opcion opcion, int catalogId) {

//        Sexo
        if(catalogId == 0) {
            setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvSexo));
            fisController.getIntegrante().setSexo(opcion);
        }

        //Estado Civil
        if(catalogId == 1) {
            setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvEstadoCivil));
            fisController.getIntegrante().setEstadoCivil(opcion);
        }

        //Nacionalidad
        if(catalogId == 3) {
            setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvNacionalidad));
            fisController.getIntegrante().setNacionalidad(opcion);
        }


        //Tipo Identificacion
        if(catalogId == 4) {
            setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvTipoIdentificacion));
            fisController.getIntegrante().setTipoIdentificacion(opcion);
        }

    }
    public PlaceholderFragment5 globalFragment;
    @NonNull
    @Override
    public FragmentManager getFragmentManager() {
        return super.getFragmentManager();
    }


    public int currentItem = 0;
    public int[] memoryScrollPos= new int[]{0,0};

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
    public static class PlaceholderFragment5 extends Fragment{
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        FISController fisController = FISController.getInstance();

        View globalRootView;
        ScrollView sv;
        String[] errorMessages = new String[]{
                "El Primer Apellido no puede estar en blanco.",
                "El Segundo Apellido no puede estar en blanco para Costarricenses.",
                "El Primer Nombre no puede estar en blanco.",
                "El Número de Identificación no puede estar en blanco.",
                "La Fecha de Nacimiento no puede estar en blanco ni ser mayor al día actual.",
                "El Sexo no puede estar en blanco.",
                "El Estado Civíl no puede estar en blanco.",
                "La Nacionalidad no puede estar en blanco.",
                "El Tipo de Identificación no puede estar en blanco",
                "El Número de Identificación para nacionales debe ser de 10 dígitos.",
                "El Número de Identificación para extranjeros debe ser mayor a 10 dígitos.",
                "El Tipo de Identificación no concuerda con la Nacionalidad.",
                "El Número de Identificación para nacionales debe iniciar con 0.",
                "El Jefe de Familia no puede ser menor a 12 años"
        };

        public static PlaceholderFragment5 newInstance(int sectionNumber) {
            PlaceholderFragment5 fragment = new PlaceholderFragment5();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment5() {
        }

        Activity_Integrante_1 parentActivity;
        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {
            parentActivity = ((Activity_Integrante_1) getActivity());

            globalRootView= inflater.inflate(R.layout.fragment_integrante_1, container, false);
            Init(globalRootView);
            return globalRootView;
        }


        void Init(final View v){
            sv = (ScrollView)v.findViewById(R.id.svGeneralCara5);
            final View vx = v;

            if (fisController.idIntegranteEditar != 0) {
                try {
                    ((TextView)v.findViewById(R.id.tvTipoIdentificacion)).setText(fisController.getIntegrante().getTipoIdentificacion());
                    ((EditText)v.findViewById(R.id.etNumeroIdentificacion)).setText(fisController.getIntegrante().getNumeroIdentificacion());
                    ((EditText)v.findViewById(R.id.etPrimerApellido)).setText(fisController.getIntegrante().getPrimerApellido());
                    ((EditText)v.findViewById(R.id.etSegundoApellido)).setText(fisController.getIntegrante().getSegundoApellido());
                    ((EditText)v.findViewById(R.id.etPrimerNombre)).setText(fisController.getIntegrante().getPrimerNombre());
                    ((EditText)v.findViewById(R.id.etSegundoNombre)).setText(fisController.getIntegrante().getSegundoNombre());
                    ((TextView)v.findViewById(R.id.tvFechaNacimiento)).setText(fisController.getIntegrante().getFechaNacimiento());


                    EditText etEdad = (EditText)v.findViewById(R.id.etEdad);
                    fisController.getIntegrante().setEdad(Integrante.getEdadPorFecha(fisController.getIntegrante().getFechaNacimiento()));
                    etEdad.setText(""+fisController.getIntegrante().getEdad());

                    ((TextView)v.findViewById(R.id.tvSexo)).setText(fisController.getIntegrante().getSexo());
                    ((TextView)v.findViewById(R.id.tvEstadoCivil)).setText(fisController.getIntegrante().getEstadoCivil());
                    ((TextView)v.findViewById(R.id.tvNacionalidad)).setText(fisController.getIntegrante().getNacionalidad());

                    if(fisController.getIntegrante().getEdad() < 12){
                        ((TextView) v.findViewById(R.id.tvEstadoCivil)).setText("No aplica");
                        ((Button) v.findViewById(R.id.btnEstadoCivil)).setEnabled(false);
                    }
                    else{
                        ((Button) v.findViewById(R.id.btnEstadoCivil)).setEnabled(true);
                        if(!fisController.getIntegrante().getIdEstadoCivil().equals("")) {
                            ((TextView) v.findViewById(R.id.tvEstadoCivil)).setText(fisController.getIntegrante().getEstadoCivil());
                        }
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

//            v.findViewById(R.id.btnBuscarPersona).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View vx) {
//                    Activity_Integrante_1 localAct = ((Activity_Integrante_1) getActivity());
//                    String identificacion = ((EditText) v.findViewById(R.id.etNumeroIdentificacion)).getText().toString();
//                    integrante = Integrante.getPersonaRegistro(identificacion);
//                    if(integrante.getPrimerNombre().equals(""))
//                        Toast.makeText(globalRootView.getContext(), "No se ha encontrado una persona con esta identificación", Toast.LENGTH_LONG).show();
//                    familiasCtrl.setCurrentIntegrante(integrante);
//                    loadData(v);
//                    parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};
//
//                    ((EditText)v.findViewById(R.id.etPrimerApellido)).requestFocus();
//
//                }
//            });

            v.findViewById(R.id.tvFechaNacimiento).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    DialogFragment newFragment = new DatePicker_Fragment();
                    newFragment.show(getFragmentManager(), "datePicker");
                }
            });


            v.findViewById(R.id.btnSexo).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_Integrante_1 localAct = ((Activity_Integrante_1) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(Integrante.getOpcionesSexo(), 0);
                    parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};

                }
            });

            v.findViewById(R.id.btnEstadoCivil).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_Integrante_1 localAct = ((Activity_Integrante_1) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(Integrante.getOpcionesEstadoCivil(), 1);
                    parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};

                }
            });

            v.findViewById(R.id.btnNacionalidad).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_Integrante_1 localAct = ((Activity_Integrante_1) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(Integrante.getOpcionesNacionalidad(), 3);
                    parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};

                }
            });

            v.findViewById(R.id.btnTipoIdentificacion).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_Integrante_1 localAct = ((Activity_Integrante_1) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(Integrante.getOpcionesTipoIdentificacion(), 4);
                    parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};
                }
            });




            v.findViewById(R.id.btnSiguiente).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {

                    if(!formIsValid(v)){
                        ScrollView sv = (ScrollView)v.findViewById(R.id.svGeneralCara5);
                        sv.scrollTo(0, 0);
                        ((LinearLayout)v.findViewById(R.id.errorMsg5)).setVisibility(View.VISIBLE);
                    }
                    else {
                        parentActivity.mDashboardDrawerFragmentCara1.saveObservaciones();
                        ((LinearLayout)v.findViewById(R.id.errorMsg5)).setVisibility(View.GONE);
                        Intent openStep = new Intent(parentActivity, Activity_Integrante_2.class);
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
            EditText et = null;
            TextView tv = null;

            et = ((EditText) v.findViewById(R.id.etPrimerApellido));
            if (et.getText().toString().trim().equals("")) {
                setError(0, et);
                error = true;
            }
            else{
                fisController.getIntegrante().setPrimerApellido(et.getText().toString());
            }

            et = ((EditText) v.findViewById(R.id.etSegundoApellido));
            if (et.getText().toString().trim().equals("") && fisController.getIntegrante().getIdNacionalidad().equals("1")) {
                setError(1, et);
                error = true;
            }
            else{
                fisController.getIntegrante().setSegundoApellido(et.getText().toString());
            }

            et = ((EditText) v.findViewById(R.id.etPrimerNombre));
            if (et.getText().toString().equals("")) {
                setError(2, et);
                error = true;
            }
            else{
                fisController.getIntegrante().setPrimerNombre(et.getText().toString());
            }

            et = ((EditText) v.findViewById(R.id.etSegundoNombre));
            fisController.getIntegrante().setSegundoNombre(et.getText().toString());


            et = ((EditText) v.findViewById(R.id.etNumeroIdentificacion));
            if(!fisController.getIntegrante().getIdTipoIdentificacion().isEmpty()) {
                if (et.getText().toString().equals("") && !fisController.getIntegrante().getIdTipoIdentificacion().equals("003")) {
                    setError(3, et);
                    error = true;
                } else if (fisController.getIntegrante().getIdTipoIdentificacion().equals("001") && et.getText().toString().length() != 10) {
                    setError(9, et);
                    error = true;
                } else if (fisController.getIntegrante().getIdTipoIdentificacion().equals("002") && et.getText().toString().length() <= 10) {
                    setError(10, et);
                    error = true;
                }
                else if(fisController.getIntegrante().getIdTipoIdentificacion().equals("001") && !et.getText().toString().startsWith("0")){
                    setError(12, et);
                    error = true;
                }
                else {
                    fisController.getIntegrante().setNumeroIdentificacion(et.getText().toString());
                }
            }

            tv = ((TextView) v.findViewById(R.id.tvFechaNacimiento));
            if (tv.getText().toString().equals("")) {
                ((TextView) v.findViewById(R.id.tvErrorFechaNacimiento)).setVisibility(View.VISIBLE);
                setError(4, ((TextView) v.findViewById(R.id.tvErrorFechaNacimiento)));
                error = true;
            }
            else{
                ((TextView) v.findViewById(R.id.tvErrorFechaNacimiento)).setVisibility(View.GONE);
                fisController.getIntegrante().setFechaNacimiento(tv.getText().toString());

                if(fisController.getIntegrante().getEdad() < 12 && (fisController.getIntegrante().getJefeFamilia() || fisController.getIntegrantes().size() == 0)){
                    ((TextView) v.findViewById(R.id.tvErrorFechaNacimiento)).setVisibility(View.VISIBLE);
                    setError(13, (TextView) v.findViewById(R.id.tvErrorFechaNacimiento));
                    error = true;
                }
            }

            tv = ((TextView) v.findViewById(R.id.tvSexo));
            if (fisController.getIntegrante().getIdSexo().equals("")) {
                setError(5, tv);
                error = true;
            }

            tv = ((TextView) v.findViewById(R.id.tvEstadoCivil));
            if (fisController.getIntegrante().getIdEstadoCivil().equals("") && fisController.getIntegrante().getEdad() > 12) {
                setError(6, tv);
                error = true;
            }

            tv = ((TextView) v.findViewById(R.id.tvNacionalidad));
            if (fisController.getIntegrante().getIdNacionalidad().equals("")) {
                setError(7, tv);
                error = true;
            }else if(fisController.getIntegrante().getIdTipoIdentificacion().equals("001") && Integer.parseInt(fisController.getIntegrante().getIdNacionalidad()) > 1){
                setError(11, tv);
                error = true;
            }else if((fisController.getIntegrante().getIdTipoIdentificacion().equals("002") || fisController.getIntegrante().getIdTipoIdentificacion().equals("003")) && fisController.getIntegrante().getIdNacionalidad().equals("1")){
                setError(11, tv);
                error = true;
            }

            tv = ((TextView) v.findViewById(R.id.tvTipoIdentificacion));
            if (fisController.getIntegrante().getIdTipoIdentificacion().equals("")) {
                setError(8, tv);
                error = true;
            }

            //familiasCtrl.setJefeFamilia(fisController.getIntegrante());

            return !error;
        }


        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);

            ((Activity_Integrante_1) activity).onSectionAttached(
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