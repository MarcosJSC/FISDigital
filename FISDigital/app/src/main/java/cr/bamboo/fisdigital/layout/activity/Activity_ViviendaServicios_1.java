package cr.bamboo.fisdigital.layout.activity;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.RadioGroup;

import cr.bamboo.fisdigital.R;
import cr.bamboo.fisdigital.layout.fragment.DashboardDrawer_Fragment;
import cr.bamboo.fisdigital.layout.fragment.NavigationDrawer_Fragment;
import cr.bamboo.fisdigital.logic.bo.ViviendaServicios;
import cr.bamboo.fisdigital.logic.global.FISController;
import cr.bamboo.fisdigital.data.dbmodel.Opcion;


public class Activity_ViviendaServicios_1 extends FISActivity
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
        setContentView(R.layout.activity_vivienda_servicios_1);

        if (fisController.idFISEditar != 0) {
            try {
                fisController.loadViviendaServicios();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        mNavigationDrawerFragmentCara1 = (NavigationDrawer_Fragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);

        mDashboardDrawerFragmentCara1 = (DashboardDrawer_Fragment)
                getFragmentManager().findFragmentById(R.id.dashboard_drawer1);

        mDashboardDrawerFragmentCara1.getArguments().putString("textoSecciones", "1/10");
        mDashboardDrawerFragmentCara1.getArguments().putString("textoPreguntas", "13/62");
        mDashboardDrawerFragmentCara1.setData();


        mTitle = getString(R.string.title_cara2);

        mNavigationDrawerFragmentCara1.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout2));

        mDashboardDrawerFragmentCara1.setUp(R.id.dashboard_drawer1, (DrawerLayout) findViewById(R.id.drawer_layout2));


        FragmentManager fragmentManager = getFragmentManager();
        globalFragment =  PlaceholderFragment2.newInstance(1);
        fragmentManager.beginTransaction()
                .replace(R.id.container2, globalFragment)
                .commit();

        mNavigationDrawerFragmentCara1.setRetainInstance(true);
        restoreActionBar();
    }

    private void setText(String description, TextView tv){
        tv.setText(description);
        tv.setTextAppearance(globalFragment.getView().getContext(), R.style.FISTextView);
        tv.setBackgroundResource(R.drawable.textview_border);
    }

    public void setMalo(RadioGroup rg1) {
        toggleVisibility(rg1, true);
        ((RadioButton)rg1.getChildAt(2)).setChecked(true);
        ((RadioButton)rg1.getChildAt(0)).setEnabled(false);
        ((RadioButton)rg1.getChildAt(1)).setEnabled(false);
        ((RadioButton)rg1.getChildAt(2)).setEnabled(false);

        for(int i = 0; i < rg1.getChildCount(); i++){
            ((RadioButton)rg1.getChildAt(i)).setEnabled(false);
        }
    }

    public static void setRadioValue(RadioGroup rg1, String estado, boolean enabled) {
        int index = 0;
        if(estado.equals("B")){
           index = 0;
        }
        if(estado.equals("R")){
            index = 1;
        }
        if(estado.equals("M")){
            index = 2;
        }
        if(estado.equals("NT")){
            index = -1;
        }

        if(index == -1) {
            toggleVisibility(rg1, false);
        }
        else {
            ((RadioButton) rg1.getChildAt(index)).setChecked(true);

            for (int i = 0; i < rg1.getChildCount(); i++) {
                ((RadioButton) rg1.getChildAt(i)).setEnabled(enabled);
            }
            toggleVisibility(rg1, true);
        }
    }

    public static void toggleVisibility(RadioGroup rg1, boolean visible) {

        rg1.setVisibility(visible ? View.VISIBLE : View.INVISIBLE );
    }

    public void enableOptions(RadioGroup rg1) {
        for(int i = 0; i < rg1.getChildCount(); i++){
            ((RadioButton)rg1.getChildAt(i)).setEnabled(true);
            ((RadioButton)rg1.getChildAt(i)).setChecked(false);
        }
        rg1.clearCheck();
        toggleVisibility(rg1, true);
    }

    @Override
    public void onNavigationDrawerItemSelected(Opcion opcion, int catalogId) {

        String codigo = opcion.getCodigo();

        //Paredes Exteriores
        if(catalogId == 0) {
            setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvParedesExteriores));
            fisController.getViviendaServicios().setParedesExteriores(opcion);

            RadioGroup rg1 = (RadioGroup) findViewById(R.id.radioEstadoParedes);
            if (codigo.equals("7")) {
                setMalo(rg1);
                fisController.getViviendaServicios().setEstadoParedes("M");

            } else if (codigo.equals("8")) {
                toggleVisibility(rg1, false);
                fisController.getViviendaServicios().setEstadoParedes("NT");
            } else {
                enableOptions(rg1);
            }

        }

        //Pisos
        if(catalogId == 1) {
            setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvPiso));
            fisController.getViviendaServicios().setPiso(opcion);

            RadioGroup rg1 = (RadioGroup) findViewById(R.id.radioEstadoPiso);
            if (codigo.equals("4")) {
                toggleVisibility(rg1, false);
                fisController.getViviendaServicios().setEstadoPiso("NT");
            } else {
                enableOptions(rg1);
            }

        }

        //Techo
        if(catalogId == 2) {
            setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvTecho));
            fisController.getViviendaServicios().setTecho(opcion);

            RadioGroup rg1 = (RadioGroup) findViewById(R.id.radioEstadoTecho);
            if (codigo.equals("4")) {
                setMalo(rg1);
                fisController.getViviendaServicios().setEstadoTecho("M");
            } else {
                enableOptions(rg1);
            }
        }

        //Cielo Raso
        if(catalogId == 3) {
            setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvCieloRaso));
            fisController.getViviendaServicios().setCieloRaso(opcion);

            RadioGroup rg1 = (RadioGroup) findViewById(R.id.radioEstadoCieloRaso);
            if (codigo.equals("2")) {
                toggleVisibility(rg1, false);
                fisController.getViviendaServicios().setEstadoCieloRaso("NT");
            } else {
                enableOptions(rg1);
            }

        }
    }

    public PlaceholderFragment2 globalFragment;
    @NonNull
    @Override
    public FragmentManager getFragmentManager() {
        return super.getFragmentManager();
    }


    public int currentItem = 0;
    public boolean alreadyValidated = false;
    public int[] memoryScrollPos= new int[]{0,0};

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    public void onSectionAttached(int number) {

        /* When something in the right menu is clicked then this is called and we can get the position of the row clicked */

        currentItem=number;

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
    public static class PlaceholderFragment2 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        View globalRootView;
        ScrollView sv;
        String[] errorMessages = new String[]{
                "Debe seleccionar un valor para la Variable Paredes Exteriores. El valor indicado para esta variable no es correcto.",
                "Debe seleccionar un valor para la Variable Piso. El valor indicado para esta variable no es correcto.",
                "Debe seleccionar un valor para la Variable Techo. El valor indicado para esta variable no es correcto.",
                "Debe seleccionar un valor para la Variable Cielo Raso. El valor indicado para esta variable no es correcto.",
                "Debe ingresar los aposentos para dormir.",
                "No se puede registrar mas de 10 aposentos",
                "Debe seleccionar una opcion"
        };

        // To validate in there are errors in the form
        boolean areErrorsInForm = false;



        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        //NavigationDrawer_Fragment parentNavigationDrawerFragment;

        //public static PlaceholderFragment newInstance(int sectionNumber, NavigationDrawer_Fragment mNavigationDrawerFragment) {
        public static PlaceholderFragment2 newInstance(int sectionNumber) {
            PlaceholderFragment2 fragment = new PlaceholderFragment2();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment2() {
        }

        Activity_ViviendaServicios_1 parentActivity;
        FISController fisController = FISController.getInstance();
        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {
            parentActivity = ((Activity_ViviendaServicios_1) getActivity());

            globalRootView= inflater.inflate(R.layout.fragment_vivienda_servicios_1, container, false);
            Init(globalRootView);
            return globalRootView;
        }

        void Init(final View v) {
            sv = (ScrollView) v.findViewById(R.id.svGeneralCara2);

            if (fisController.idFISEditar != 0) {
                try {

                    if (!fisController.getViviendaServicios().getIdParedesExteriores().isEmpty()) {
                        ((TextView) v.findViewById(R.id.tvParedesExteriores)).setText(fisController.getViviendaServicios().getParedesExteriores());

                        //Estado Paredes
                        int idParedes = Integer.parseInt(fisController.getViviendaServicios().getIdParedesExteriores());
                        if (idParedes == 7) {
                            setRadioValue((RadioGroup) v.findViewById(R.id.radioEstadoParedes), fisController.getViviendaServicios().getEstadoParedes(), false);
                        } else if (idParedes == 8) {
                            setRadioValue((RadioGroup) v.findViewById(R.id.radioEstadoParedes), fisController.getViviendaServicios().getEstadoParedes(), false);
                        } else {
                            setRadioValue((RadioGroup) v.findViewById(R.id.radioEstadoParedes), fisController.getViviendaServicios().getEstadoParedes(), true);
                        }
                    }
                    if (!fisController.getViviendaServicios().getIdPiso().isEmpty()) {
                        ((TextView) v.findViewById(R.id.tvPiso)).setText(fisController.getViviendaServicios().getPiso());

                        //Estado Piso
                        int idPiso = Integer.parseInt(fisController.getViviendaServicios().getIdPiso());
                        if (idPiso == 4) {
                            setRadioValue((RadioGroup) v.findViewById(R.id.radioEstadoPiso), fisController.getViviendaServicios().getEstadoPiso(), false);
                        } else {
                            setRadioValue((RadioGroup) v.findViewById(R.id.radioEstadoPiso), fisController.getViviendaServicios().getEstadoPiso(), true);
                        }
                    }
                    if (!fisController.getViviendaServicios().getIdTecho().isEmpty()) {
                        ((TextView) v.findViewById(R.id.tvTecho)).setText(fisController.getViviendaServicios().getTecho());

                        //Estado Techo
                        int idTecho = Integer.parseInt(fisController.getViviendaServicios().getIdTecho());
                        if (idTecho == 4) {
                            setRadioValue((RadioGroup) v.findViewById(R.id.radioEstadoTecho), fisController.getViviendaServicios().getEstadoTecho(), false);
                        } else {
                            setRadioValue((RadioGroup) v.findViewById(R.id.radioEstadoTecho), fisController.getViviendaServicios().getEstadoTecho(), true);
                        }
                    }
                    if (!fisController.getViviendaServicios().getIdCieloRaso().isEmpty()) {
                        ((TextView) v.findViewById(R.id.tvCieloRaso)).setText(fisController.getViviendaServicios().getCieloRaso());

                        //Estado Cielo Raso
                        int idCieloRaso = Integer.parseInt(fisController.getViviendaServicios().getIdCieloRaso());
                        if (idCieloRaso == 2) {
                            setRadioValue((RadioGroup) v.findViewById(R.id.radioEstadoCieloRaso), fisController.getViviendaServicios().getEstadoCieloRaso(), false);
                        } else {
                            setRadioValue((RadioGroup) v.findViewById(R.id.radioEstadoCieloRaso), fisController.getViviendaServicios().getEstadoCieloRaso(), true);
                        }
                    }

                    if (fisController.getViviendaServicios().getAposentosDormir() != 0) {
                        ((EditText) v.findViewById(R.id.etAposentos1)).setText("" + fisController.getViviendaServicios().getAposentosDormir());
                    }
                    if (fisController.getViviendaServicios().getAposentosOtros() != 0) {
                        ((EditText) v.findViewById(R.id.etAposentos2)).setText("" + fisController.getViviendaServicios().getAposentosOtros());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            v.findViewById(R.id.btnParedesExteriores).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_ViviendaServicios_1 localAct = ((Activity_ViviendaServicios_1) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(ViviendaServicios.getOpcionesParedes(), 0);
                    parentActivity.memoryScrollPos = new int[]{sv.getScrollX(), sv.getScrollY()};

                }
            });

            v.findViewById(R.id.btnPiso).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_ViviendaServicios_1 localAct = ((Activity_ViviendaServicios_1) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(ViviendaServicios.getOpcionesPiso(fisController.getViviendaServicios()), 1);
                    parentActivity.memoryScrollPos = new int[]{sv.getScrollX(), sv.getScrollY()};
                }
            });

            v.findViewById(R.id.btnTecho).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_ViviendaServicios_1 localAct = ((Activity_ViviendaServicios_1) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(ViviendaServicios.getOpcionesTecho(fisController.getViviendaServicios()), 2);
                    parentActivity.memoryScrollPos = new int[]{sv.getScrollX(), sv.getScrollY()};
                }
            });

            v.findViewById(R.id.btnCieloRaso).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_ViviendaServicios_1 localAct = ((Activity_ViviendaServicios_1) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(ViviendaServicios.getOpcionesCieloRaso(fisController.getViviendaServicios()), 3);
                    parentActivity.memoryScrollPos = new int[]{sv.getScrollX(), sv.getScrollY()};
                }
            });


            v.findViewById(R.id.btnSiguiente).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {

                    viewFragment = v;
                    if (!formIsValid(v)) {
                        ScrollView sv = (ScrollView) v.findViewById(R.id.svGeneralCara2);
                        sv.scrollTo(0, 0);
                        ((LinearLayout) v.findViewById(R.id.errorMsg2)).setVisibility(View.VISIBLE);
                    } else {
                        parentActivity.mDashboardDrawerFragmentCara1.saveObservaciones();
                        Intent openStep = new Intent(parentActivity, Activity_ViviendaServicios_2.class);
                        startActivity(openStep);
                    }
                }
            });


            v.findViewById(R.id.etAposentos1).setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View vx, int key, KeyEvent e) {
                    if (vx == null) return false;
                    EditText componentVal = (EditText) vx;
                    EditText componentVal2 = (EditText) v.findViewById(R.id.etAposentos2);
                    int aposentosDormir = 0;
                    int aposentosOtros = 0;
                    int total = 0;

                    if (!componentVal.getText().toString().equals("")) {
                        aposentosDormir = Integer.parseInt(componentVal.getText().toString());
                    } else {
                        aposentosDormir = 0;
                    }

                    if (!componentVal2.getText().toString().equals("")) {
                        aposentosOtros = Integer.parseInt(componentVal2.getText().toString());
                    } else {
                        aposentosOtros = 0;
                    }

                    total = aposentosDormir + aposentosOtros;

                    View parentView = (View) v.getParent();
                    TextView totalTextField = (TextView) parentView.findViewById(R.id.tvTotalAposentos);
                    totalTextField.setText("Total de Aposentos : " + String.valueOf(total));

                    return false;
                }

            });

            v.findViewById(R.id.etAposentos2).setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View vx, int key, KeyEvent e) {
                    if (v == null) return false;
                    EditText componentVal = (EditText) v.findViewById(R.id.etAposentos1);
                    EditText componentVal2 = (EditText) vx;

                    int aposentosDormir = 0;
                    int aposentosOtros = 0;
                    int total = 0;

                    if (!componentVal.getText().toString().equals("")) {
                        aposentosDormir = Integer.parseInt(componentVal.getText().toString());
                    } else {
                        aposentosDormir = 0;
                    }

                    if (!componentVal2.getText().toString().equals("")) {
                        aposentosOtros = Integer.parseInt(componentVal2.getText().toString());
                    } else {
                        aposentosOtros = 0;
                    }

                    total = aposentosDormir + aposentosOtros;

                    View parentView = (View) v.getParent();
                    TextView totalTextField = (TextView) parentView.findViewById(R.id.tvTotalAposentos);
                    totalTextField.setText("Total de Aposentos : " + String.valueOf(total));


                    return false;
                }

            });


            sv.post(new Runnable() {
                public void run() {
                    sv.scrollTo(parentActivity.memoryScrollPos[0], parentActivity.memoryScrollPos[1]);
                }
            });
        }


        View viewFragment;

        boolean formIsValid(View v){

            TextView tv = null;
            EditText et = null;
            int selectedId = 0;

            v=globalRootView;
            boolean error=false;

            tv = ((TextView)v.findViewById(R.id.tvParedesExteriores));
            if(fisController.getViviendaServicios().getIdParedesExteriores().equals("")){
                setError(0,tv);
                error=true;
            }
            else{
                tv =(TextView)v.findViewById(R.id.errorRbtParedes);
                selectedId = ((RadioGroup)v.findViewById(R.id.radioEstadoParedes)).getCheckedRadioButtonId();
                if(selectedId == -1){
                    if(Integer.parseInt(fisController.getViviendaServicios().getIdParedesExteriores()) <= 7){
                        setError(6,tv, true);
                        error=true;
                    }else{
                        fisController.getViviendaServicios().setEstadoParedes("NT");
                        tv.setText("");
                        tv.setVisibility(View.GONE);
                    }
                }
                else{
                    String value = ((RadioButton) v.findViewById(selectedId)).getText().toString();
                    fisController.getViviendaServicios().setEstadoParedes(value.substring(0, 1));
                    tv.setText("");
                    tv.setVisibility(View.GONE);
                }
            }

            tv = ((TextView) v.findViewById(R.id.tvPiso));
            if(fisController.getViviendaServicios().getIdPiso().equals("")){
                setError(1,tv);
                error=true;
            }
            else{
                tv =(TextView)v.findViewById(R.id.errorRbtPiso);
                selectedId = ((RadioGroup)v.findViewById(R.id.radioEstadoPiso)).getCheckedRadioButtonId();
                if(selectedId == -1){
                    if(Integer.parseInt(fisController.getViviendaServicios().getIdPiso())< 4){
                        setError(6,tv, true);
                        error=true;
                    }
                    else {
                        fisController.getViviendaServicios().setEstadoPiso("NT");
                        tv.setText("");
                        tv.setVisibility(View.GONE);
                    }
                }
                else{
                    String value = ((RadioButton) v.findViewById(selectedId)).getText().toString();
                    fisController.getViviendaServicios().setEstadoPiso(value.substring(0, 1));
                    tv.setText("");
                    tv.setVisibility(View.GONE);
                }
            }

            tv = ((TextView)v.findViewById(R.id.tvTecho));
            if(fisController.getViviendaServicios().getIdTecho().equals("")){
                setError(2,tv);
                error=true;
            }
            else{
                tv =(TextView)v.findViewById(R.id.errorRbtTecho);
                selectedId = ((RadioGroup)v.findViewById(R.id.radioEstadoTecho)).getCheckedRadioButtonId();
                if(selectedId == -1) {
                    setError(6, tv, true);
                    error = true;
                }
                else{
                    String value = ((RadioButton) v.findViewById(selectedId)).getText().toString();
                    fisController.getViviendaServicios().setEstadoTecho(value.substring(0, 1));
                    tv.setText("");
                    tv.setVisibility(View.GONE);
                }
            }

            tv = ((TextView)v.findViewById(R.id.tvCieloRaso));
            if(fisController.getViviendaServicios().getIdCieloRaso().equals("")){
                setError(3,tv);
                error=true;
            }
            else{
                tv =(TextView)v.findViewById(R.id.errorRbtCielo);
                selectedId = ((RadioGroup)v.findViewById(R.id.radioEstadoCieloRaso)).getCheckedRadioButtonId();
                if(selectedId == -1){
                    if(fisController.getViviendaServicios().getIdCieloRaso().equals("1")){
                        setError(6,tv, true);
                        error=true;
                    }
                    else {
                        fisController.getViviendaServicios().setEstadoCieloRaso("NT");
                        tv.setText("");
                        tv.setVisibility(View.GONE);
                    }
                }
                else{
                    String value = ((RadioButton) v.findViewById(selectedId)).getText().toString();
                    fisController.getViviendaServicios().setEstadoCieloRaso(value.substring(0, 1));
                    tv.setText("");
                    tv.setVisibility(View.GONE);
                }

            }

            et =(EditText)v.findViewById(R.id.etAposentos1);
            if(et.getText().toString().equals("") || et.getText().toString().equals("0")){
                setError(4, et);
                error=true;
            }
            else{
                EditText componentVal = (EditText) v.findViewById(R.id.etAposentos1);
                EditText componentVal2 = (EditText) v.findViewById(R.id.etAposentos2);
                int aposentosDormir = Integer.parseInt(componentVal.getText().toString());
                int aposentosOtros = 0;

                if(!componentVal2.getText().toString().equals("")){
                    aposentosOtros = Integer.parseInt(componentVal2.getText().toString());
                }

                if((aposentosDormir + aposentosOtros) > 10 ){
                    setError(5,et);
                    error=true;
                }
                else{
                    fisController.getViviendaServicios().setAposentosDormir(aposentosDormir);
                    fisController.getViviendaServicios().setAposentosOtros(aposentosOtros);
                }
            }

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

        void setError(int idErrorMsg, TextView component,boolean rb) {
            component.setText(errorMessages[idErrorMsg]);
            component.setTextAppearance(globalRootView.getContext(), R.style.FISTextViewErrorRB);
            component.setBackgroundResource(R.drawable.textview_border_error);
            component.setVisibility(View.VISIBLE);
        }


        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);

        ((Activity_ViviendaServicios_1) activity).onSectionAttached(
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