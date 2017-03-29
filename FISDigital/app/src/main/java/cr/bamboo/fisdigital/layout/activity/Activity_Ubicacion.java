package cr.bamboo.fisdigital.layout.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cr.bamboo.fisdigital.R;
import cr.bamboo.fisdigital.layout.fragment.DashboardDrawer_Fragment;
import cr.bamboo.fisdigital.layout.fragment.NavigationDrawer_Fragment;
import cr.bamboo.fisdigital.logic.bo.Ubicacion;
import cr.bamboo.fisdigital.logic.global.FISController;
import cr.bamboo.fisdigital.data.dbmodel.Opcion;

public class Activity_Ubicacion extends FISActivity
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
        setContentView(R.layout.activity_ubicacion);

        if (fisController.idFISEditar != 0) {
            try {
                fisController.loadUbicacion();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else{
            fisController.init();
        }

        mNavigationDrawerFragmentCara1 = (NavigationDrawer_Fragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);


        mDashboardDrawerFragmentCara1 = (DashboardDrawer_Fragment)
                getFragmentManager().findFragmentById(R.id.dashboard_drawer1);

        mDashboardDrawerFragmentCara1.getArguments().putString("textoSecciones", "0/10");
        mDashboardDrawerFragmentCara1.getArguments().putString("textoPreguntas", "0/62");
        mDashboardDrawerFragmentCara1.setData();

        mTitle = getString(R.string.title_cara1);
        restoreActionBar();

        // Set up the drawer.
        mNavigationDrawerFragmentCara1.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        mDashboardDrawerFragmentCara1.setUp(R.id.dashboard_drawer1, (DrawerLayout) findViewById(R.id.drawer_layout));

        FragmentManager fragmentManager = getFragmentManager();
        globalFragment = PlaceholderFragment.newInstance(1);
        fragmentManager.beginTransaction()
                .replace(R.id.container, globalFragment)
                .commit();

        mNavigationDrawerFragmentCara1.setRetainInstance(true);
        mDashboardDrawerFragmentCara1.setRetainInstance(true);

        restoreActionBar();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent openStep = new Intent(this, Activity_Login.class);
                openStep.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityForResult(openStep, 0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setText(String description, TextView tv) {
        tv.setText(description);
        tv.setTextAppearance(globalFragment.getView().getContext(), R.style.FISTextView);
        tv.setBackgroundResource(R.drawable.textview_border);
    }

    @Override
    public void onNavigationDrawerItemSelected(Opcion opcion, int catalogId) {

        switch (catalogId) {
            case 0:
                //Provincia
                setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvProvincia));
                fisController.getUbicacion().setProvincia(opcion);
                resetUbicacion(5);
                break;
            case 1:
                //Canton
                setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvCanton));
                fisController.getUbicacion().setCanton(opcion);

                //Region Mideplan
                Opcion region = Ubicacion.getRegionMideplan(fisController.getUbicacion().getIdProvincia(), fisController.getUbicacion().getIdCanton());
                setText(region.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvRegion));
                fisController.getUbicacion().setRegion(region);
                resetUbicacion(4);
                break;
            case 2:
                //Distrito
                setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvDistrito));
                fisController.getUbicacion().setDistrito(opcion);

                //Gerencia Regional
                Opcion gerencia = Ubicacion.getGerenciaRegional(fisController.getUbicacion().getIdProvincia(), fisController.getUbicacion().getIdCanton(), fisController.getUbicacion().getIdDistrito());
                setText(gerencia.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvGerenciaRegional));
                fisController.getUbicacion().setGerenciaRegional(gerencia);
                resetUbicacion(3);
                break;
            case 3:
                //Barrio
                setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvBarrio));
                fisController.getUbicacion().setBarrio(opcion);
                resetUbicacion(2);
                break;
            case 4:
                //Caserio
                setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvCaserio));
                fisController.getUbicacion().setCaserio(opcion);
                break;
            case 5:
                //Zona
                setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvZona));
                fisController.getUbicacion().setZona(opcion);
                break;
            case 6:
                //Vivienda en Riesgo
                setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvViviendaRiesgo));
                fisController.getUbicacion().setViviendaRiesgo(opcion);
                break;
        }

    }

    public PlaceholderFragment globalFragment;

    @NonNull
    @Override
    public FragmentManager getFragmentManager() {
        return super.getFragmentManager();
    }


    public int currentItem = 0;
    public boolean alreadyValidated = false;
    public int[] memoryScrollPos = new int[]{0, 0};

    public void onSectionAttached(int number) {

        currentItem = number;

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
            getMenuInflater().inflate(R.menu.fiscara1, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    private void resetUbicacion(int deleteDepth) {

        if (deleteDepth > 1) {
            ((TextView) globalFragment.getView().findViewById(R.id.tvCaserio)).setText("");
            fisController.getUbicacion().setIdCaserio("");
        }
        if (deleteDepth > 2) {
            ((TextView) globalFragment.getView().findViewById(R.id.tvBarrio)).setText("");
            fisController.getUbicacion().setIdBarrio("");
        }
        if (deleteDepth > 3) {
            ((TextView) globalFragment.getView().findViewById(R.id.tvDistrito)).setText("");
            fisController.getUbicacion().setIdDistrito("");

            ((TextView) globalFragment.getView().findViewById(R.id.tvGerenciaRegional)).setText("");
            fisController.getUbicacion().setIdGerenciaRegional("");
        }

        if (deleteDepth > 4) {
            //Canton
            ((TextView) globalFragment.getView().findViewById(R.id.tvCanton)).setText("");
            fisController.getUbicacion().setIdCanton("");

            ((TextView) globalFragment.getView().findViewById(R.id.tvRegion)).setText("");
            fisController.getUbicacion().setIdRegion("");
        }

    }


    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";
        private FISController fisController = FISController.getInstance();
        private boolean canGetLocation;

        View globalRootView;
        ScrollView sv;
        String[] errorMessages = new String[]{
                "No ha seleccionado ningún valor válido para la variable Gerencia.",
                "No ha seleccionado ningún valor válido para la variable Region.",
                "No ha seleccionado ningún valor válido para la variable Provincia.",
                "No ha seleccionado ningún valor válido para la variable Cantón.",
                "No ha seleccionado ningún valor válido para la variable Distrito.",
                "No ha seleccionado ningún valor válido para la variable Barrio.",
                "No ha seleccionado ningún valor válido para la variable Caserio.",
                "Debe indicar la Zona. La variable Zona no puede quedar en blanco.",
                "Debe ingresar una dirección.",
                "Debe ingresar el número de vivienda",
                "No ha seleccionado ningún valor válido para la variable Vivienda en Riesgo.",
                "El número de vivienda debe contener números primero y luego letras. Ej. 12A",
                "El número esta fuera del rango permitido."
        };


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        Activity_Ubicacion parentActivity;

        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {
            parentActivity = ((Activity_Ubicacion) getActivity());

            globalRootView = inflater.inflate(R.layout.fragment_ubicacion, container, false);
            Init(globalRootView);
            return globalRootView;
        }

        void Init(final View v) {

            if (fisController.idFISEditar != 0) {
                try {
                    ((TextView) v.findViewById(R.id.tvGerenciaRegional)).setText(fisController.getUbicacion().getGerenciaRegional());
                    ((TextView) v.findViewById(R.id.tvRegion)).setText(fisController.getUbicacion().getRegion());
                    ((TextView) v.findViewById(R.id.tvProvincia)).setText(fisController.getUbicacion().getProvincia());
                    ((TextView) v.findViewById(R.id.tvCanton)).setText(fisController.getUbicacion().getCanton());
                    ((TextView) v.findViewById(R.id.tvDistrito)).setText(fisController.getUbicacion().getDistrito());
                    ((TextView) v.findViewById(R.id.tvBarrio)).setText(fisController.getUbicacion().getBarrio());
                    ((TextView) v.findViewById(R.id.tvCaserio)).setText(fisController.getUbicacion().getCaserio());
                    ((TextView) v.findViewById(R.id.tvZona)).setText(fisController.getUbicacion().getZona());
                    ((EditText) v.findViewById(R.id.etDireccionExacta)).setText(fisController.getUbicacion().getDireccion());
                    ((EditText) v.findViewById(R.id.etNumeroVivienda)).setText(fisController.getUbicacion().getNumeroVivienda());
                    ((TextView) v.findViewById(R.id.tvViviendaRiesgo)).setText(fisController.getUbicacion().getViviendaRiesgo());
                    ((EditText) v.findViewById(R.id.etNumeroFolio)).setText(String.valueOf(fisController.getUbicacion().getNumeroFolio()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            Location location = getLocation(globalRootView.getContext());
            sv = (ScrollView) v.findViewById(R.id.svGeneral);

            EditText etLatitud = (EditText) v.findViewById(R.id.etLatitud);
            if (etLatitud != null) {
                etLatitud.setText(String.valueOf(fisController.getLatitude()));
            }

            EditText etLongitud = (EditText) v.findViewById(R.id.etLongitud);
            if (etLongitud != null) {
                etLongitud.setText(String.valueOf(fisController.getLongitude()));
            }

            v.findViewById(R.id.btnProvincia).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_Ubicacion localAct = ((Activity_Ubicacion) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(Ubicacion.getProvincias(), 0);
                    parentActivity.memoryScrollPos = new int[]{sv.getScrollX(), sv.getScrollY()};
                }
            });

            v.findViewById(R.id.btnCanton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    String idProvincia = fisController.getUbicacion().getIdProvincia();
                    if (idProvincia.equals("")) {
                        return;
                    }
                    Activity_Ubicacion localAct = ((Activity_Ubicacion) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(Ubicacion.getCantones(idProvincia), 1);
                    parentActivity.memoryScrollPos = new int[]{sv.getScrollX(), sv.getScrollY()};
                }
            });

            v.findViewById(R.id.btnDistrito).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    String idProvincia = fisController.getUbicacion().getIdProvincia();
                    String idCanton = fisController.getUbicacion().getIdCanton();
                    if (idProvincia.equals("") || idCanton.equals("")) {
                        return;
                    }
                    Activity_Ubicacion localAct = ((Activity_Ubicacion) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(Ubicacion.getDistritos(idProvincia, idCanton), 2);
                    parentActivity.memoryScrollPos = new int[]{sv.getScrollX(), sv.getScrollY()};

                }
            });

            v.findViewById(R.id.btnBarrio).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    String idProvincia = fisController.getUbicacion().getIdProvincia();
                    String idCanton = fisController.getUbicacion().getIdCanton();
                    String idDistrito = fisController.getUbicacion().getIdDistrito();
                    if (idProvincia.equals("") || idCanton.equals("") || idDistrito.equals("")) {
                        return;
                    }
                    Activity_Ubicacion localAct = ((Activity_Ubicacion) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(Ubicacion.getBarrios(idProvincia, idCanton, idDistrito), 3);
                    parentActivity.memoryScrollPos = new int[]{sv.getScrollX(), sv.getScrollY()};
                }
            });

            v.findViewById(R.id.btnCaserio).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    String idProvincia = fisController.getUbicacion().getIdProvincia();
                    String idCanton = fisController.getUbicacion().getIdCanton();
                    String idDistrito = fisController.getUbicacion().getIdDistrito();
                    String idBarrio = fisController.getUbicacion().getIdBarrio();
                    if (idProvincia.equals("") || idCanton.equals("") || idDistrito.equals("") || idBarrio.equals("")) {
                        return;
                    }
                    Activity_Ubicacion localAct = ((Activity_Ubicacion) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(Ubicacion.getCaserios(idProvincia, idCanton, idDistrito, idBarrio), 4);
                    parentActivity.memoryScrollPos = new int[]{sv.getScrollX(), sv.getScrollY()};
                }
            });

            v.findViewById(R.id.btnZona).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_Ubicacion localAct = ((Activity_Ubicacion) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(Ubicacion.getZonas(), 5);
                    parentActivity.memoryScrollPos = new int[]{sv.getScrollX(), sv.getScrollY()};
                }
            });

            v.findViewById(R.id.btnViviendaRiesgo).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    Activity_Ubicacion localAct = ((Activity_Ubicacion) getActivity());
                    localAct.mNavigationDrawerFragmentCara1.Open(Ubicacion.getViviendaRiesgos(), 6);
                    parentActivity.memoryScrollPos = new int[]{sv.getScrollX(), sv.getScrollY()};
                }
            });


            v.findViewById(R.id.btnSiguiente).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vx) {
                    viewFragment = v;

                    if (!formIsValid(v)) {
                        ScrollView sv = (ScrollView) v.findViewById(R.id.svGeneral);
                        sv.scrollTo(0, 0);
                        ((LinearLayout) v.findViewById(R.id.errorMsg)).setVisibility(View.VISIBLE);
                        v.clearFocus();
                    } else {
                        parentActivity.mDashboardDrawerFragmentCara1.saveObservaciones();
                        fisController.saveUbicacion();
                        Intent openStep = new Intent(parentActivity, Activity_ViviendaServicios_1.class);
                        startActivity(openStep);
                    }

                }
            });

            sv.post(new Runnable() {
                public void run() {
                    sv.scrollTo(parentActivity.memoryScrollPos[0], parentActivity.memoryScrollPos[1]);
                }
            });

            ((EditText) parentActivity.findViewById(R.id.txtFilter)).setText("");

        }

        public Location getLocation(Context mContext) {
            //Context mContext =
            final LocationListener mLocationListener = new android.location.LocationListener() {
                @Override
                public void onLocationChanged(final Location location) {
                    //your code here
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            };
            Location location = null;
            try {
                LocationManager locationManager = (LocationManager) mContext
                        .getSystemService(LOCATION_SERVICE);

                // getting GPS status
                boolean isGPSEnabled = locationManager
                        .isProviderEnabled(LocationManager.GPS_PROVIDER);

                // getting network status
                boolean isNetworkEnabled = locationManager
                        .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

                if (!isGPSEnabled && !isNetworkEnabled) {
                    // no network provider is enabled
                } else {
                    this.canGetLocation = true;
                    if (isNetworkEnabled) {
                        locationManager.requestLocationUpdates(
                                LocationManager.NETWORK_PROVIDER,
                                0,
                                0, mLocationListener);
                        Log.d("Network", "Network Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                    // if GPS Enabled get lat/long using GPS Services
                    if (isGPSEnabled) {
                        if (location == null) {
                            locationManager.requestLocationUpdates(
                                    LocationManager.GPS_PROVIDER,
                                    0,
                                    0, mLocationListener);
                            Log.d("GPS", "GPS Enabled");
                            if (locationManager != null) {
                                location = locationManager
                                        .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                                if (location != null) {
                                    latitude = location.getLatitude();
                                    longitude = location.getLongitude();
                                }
                            }
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return location;
        }

        public double latitude, longitude;

        private void assignLocation(double _latitude, double _longitude) {
            latitude = _latitude;
            longitude = _longitude;
        }

        View viewFragment;

        boolean formIsValid(View v) {

            TextView tv = null;
            EditText et = null;

            v = globalRootView;
            boolean error = false;

            tv = ((TextView) v.findViewById(R.id.tvGerenciaRegional));
            if (fisController.getUbicacion().getIdGerenciaRegional().equals("")) {
                setError(0, tv);
                error = true;
            }

            tv = ((TextView) v.findViewById(R.id.tvRegion));
            if (fisController.getUbicacion().getIdRegion().equals("")) {
                setError(1, tv);
                error = true;
            }

            tv = ((TextView) v.findViewById(R.id.tvProvincia));
            if (fisController.getUbicacion().getIdProvincia().equals("")) {
                setError(2, tv);
                error = true;
            }

            tv = ((TextView) v.findViewById(R.id.tvCanton));
            if (fisController.getUbicacion().getIdCanton().equals("")) {
                setError(3, tv);
                error = true;
            }

            tv = ((TextView) v.findViewById(R.id.tvDistrito));
            if (fisController.getUbicacion().getIdDistrito().equals("")) {
                setError(4, tv);
                error = true;
            }

            tv = ((TextView) v.findViewById(R.id.tvBarrio));
            if (fisController.getUbicacion().getIdBarrio().equals("")) {
                setError(5, tv);
                error = true;
            }

            tv = ((TextView) v.findViewById(R.id.tvCaserio));
            if (fisController.getUbicacion().getIdCaserio().equals("")) {
                setError(6, tv);
                error = true;
            }

            tv = ((TextView) v.findViewById(R.id.tvZona));
            if (fisController.getUbicacion().getIdZona().equals("")) {
                setError(7, tv);
                error = true;
            }

            et = ((EditText) v.findViewById(R.id.etDireccionExacta));
            if (et.getText().toString().equals("")) {
                fisController.getUbicacion().setDireccion("");
                setError(8, et);
                error = true;
            } else {
                fisController.getUbicacion().setDireccion(et.getText().toString());
            }

            et = ((EditText) v.findViewById(R.id.etNumeroVivienda));
            if (!et.getText().toString().equals("")) {
                Pattern p = Pattern.compile("^([0-9]+[a-z]*)", Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(et.getText().toString());
                if (!m.matches()) {
                    setError(11, et);
                    error = true;
                }
            }
            fisController.getUbicacion().setNumeroVivienda(et.getText().toString());

            tv = ((TextView) v.findViewById(R.id.tvViviendaRiesgo));
            if (fisController.getUbicacion().getIdViviendaRiesgo().equals("")) {
                setError(10, tv);
                error = true;
            }

            et = ((EditText) v.findViewById(R.id.etNumeroFolio));
            if (et.getText().toString().equals("")) {
                fisController.getUbicacion().setNumeroFolio(0);
            } else {
                if(Double.parseDouble(et.getText().toString()) > 999999999) {
                    setError(12, et);
                    error = true;
                }
                else {
                    fisController.getUbicacion().setNumeroFolio(Integer.parseInt(et.getText().toString()));
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

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);

            ((Activity_Ubicacion) activity).onSectionAttached(
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