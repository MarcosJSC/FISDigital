package cr.bamboo.fisdigital.layout.fragment;


import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cr.bamboo.fisdigital.R;
import cr.bamboo.fisdigital.layout.adapter.Catalogo_ListViewAdapter;
import cr.bamboo.fisdigital.logic.global.FISController;
import cr.bamboo.fisdigital.data.dbmodel.Opcion;

/**
 * Created by Jbeita on 26/03/2015.
 * El Navigation drawer fragment contiene el codigo del
 * menu que muestra los catalogos
 */

/**
 * Fragment used for managing interactions for and presentation of a navigation drawer.
 * See the <a href="https://developer.android.com/design/patterns/navigation-drawer.html#Interaction">
 * design guidelines</a> for a complete explanation of the behaviors implemented here.
 */
public class NavigationDrawer_Fragment extends Fragment {

    /**
     * Remember the position of the selected item.
     */
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    /**
     * Per the design guidelines, you should show the drawer on launch until the user manually
     * expands it. This shared preference tracks this.
     */
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";

    /**
     * A pointer to the current callbacks instance (the Activity).
     */
    private NavigationDrawerCallbacks mCallbacks;

    /**
     * Helper component that ties the action bar to the navigation drawer.
     */
    private ActionBarDrawerToggle mDrawerToggle;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    private View mFragmentContainerView;

    private int mCurrentSelectedPosition;
    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;

    private List<Opcion> currentList;
    private int selectedCatalog;
    private boolean isSpecialCatalog;

    private String toFilter;

    public NavigationDrawer_Fragment() {
        mCurrentSelectedPosition = 0;
        selectedCatalog = -1;
        isSpecialCatalog =  false;
    }

    public void toogleButton(boolean visible) {
        if (getView() != null) {
            Button btnBuscar = (Button) getView().findViewById(R.id.btn_buscar_catalogos);
            if(visible){
                btnBuscar.setVisibility(View.VISIBLE);
            }
            else{
                btnBuscar.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Read in the flag indicating whether or not the user has demonstrated awareness of the
        // drawer. See PREF_USER_LEARNED_DRAWER for details.
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Indicate that this fragment would like to influence the set of actions in the action bar.
        setHasOptionsMenu(true);
    }
    LayoutInflater globalInflater;
    ViewGroup globalContainer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        globalInflater=inflater;
        globalContainer=container;
        final LinearLayout viewParent = (LinearLayout) inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        mDrawerListView = (ListView)viewParent.findViewById(R.id.leftMenu);

        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
                ((EditText) viewParent.findViewById(R.id.txtFilter)).setText("");
            }
        });

        mDrawerListView.setSelection(0);

        EditText filterEditText = (EditText) viewParent.findViewById(R.id.txtFilter);

        Button btnBuscar = (Button) viewParent.findViewById(R.id.btn_buscar_catalogos);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vx) {
                List<Opcion> query = null;
                String descripcion = ((EditText) viewParent.findViewById(R.id.txtFilter)).getText().toString().trim();
                if(selectedCatalog == 3){
                    query = FISController.getInstance().getOficios(descripcion);
                }
                else{
                    query = FISController.getInstance().getCentrosEnsenanza(descripcion);
                }
                currentList = query;
                Catalogo_ListViewAdapter adapter = new Catalogo_ListViewAdapter(query, getActivity());
                mDrawerListView.setAdapter(adapter);
                mDrawerListView.setItemChecked(0, true);
                mDrawerListView.setSelection(0);
                mDrawerLayout.setSelected(false);
            }
        });

        TextWatcher watch = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(!isSpecialCatalog) {
                    EditText currentInput = ((EditText) viewParent.findViewById(R.id.txtFilter));

                    toFilter = currentInput.getText().toString();
                    toFilter = toFilter.toLowerCase();
                /* filter the list */

                    if (currentList != null) {

                        List<Opcion> list = GetFilteredList(toFilter);

                        Catalogo_ListViewAdapter adapter = new Catalogo_ListViewAdapter(list, getActivity());
                        mDrawerListView.setAdapter(adapter);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        filterEditText.addTextChangedListener(watch);

        return viewParent;
    }
    List<Opcion> GetFilteredList(String toFilter){
        if(toFilter == null || toFilter.equals(""))
            return currentList;

        List<Opcion> result = new ArrayList<Opcion>();

        for (Opcion opcion: currentList) {
            String lowerWord = opcion.getDescripcion().toLowerCase();
            if (lowerWord .matches(toFilter + "(.*)")) {
                result.add(opcion);
            }
        }

        return result;
    }
    String[] firstList = new String[]{};

    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    /**
     * Users of this fragment must call this method to set up the navigation drawer interactions.
     *
     * @param fragmentId   The android:id of this fragment in its activity's layout.
     * @param drawerLayout The DrawerLayout containing this fragment's UI.
     */
    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the navigation drawer and the action bar app icon.
        mDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),                    /* host Activity */
                mDrawerLayout,                    /* DrawerLayout object */
                R.drawable.ic_drawer,             /* nav drawer image to replace 'Up' caret */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) {
                    return;
                }

                getActivity().invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) {
                    return;
                }

                if (!mUserLearnedDrawer) {
                    // The user manually opened the drawer; store this flag to prevent auto-showing
                    // the navigation drawer automatically in the future.
                    mUserLearnedDrawer = true;
                    SharedPreferences sp = PreferenceManager
                            .getDefaultSharedPreferences(getActivity());
                    sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).apply();
                }

                getActivity().invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }
        };

        // If the user hasn't 'learned' about the drawer, open it to introduce them to the drawer,
        // per the navigation drawer design guidelines.
        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(mFragmentContainerView);
        }

        // Defer code dependent on restoration of previous instance state.
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }
    /* this is a custom function to init the right list on demand */
    public void Open(String[] list){
        firstList = list;

        mDrawerListView.setSelection(0);

        mDrawerListView.setSelected(false);


        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });

        mDrawerListView.setAdapter(new ArrayAdapter<String>(
                getActionBar().getThemedContext(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                firstList
        ));
        mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);

        mDrawerLayout.openDrawer(mFragmentContainerView);
    }

    public void Open(List<Opcion> list, int catalogId){
        toogleButton(false);
        Catalogo_ListViewAdapter adapter = new Catalogo_ListViewAdapter(list, this.getActivity());
        currentList = list;
        selectedCatalog = catalogId;
        mDrawerListView.setAdapter(adapter);
        //mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);
        mDrawerListView.setItemChecked(0, true);
        mDrawerListView.setSelection(0);
        mDrawerLayout.openDrawer(mFragmentContainerView);
        mDrawerLayout.setSelected(false);
    }

    public void Open(int catalogId){
        isSpecialCatalog =  true;
        toogleButton(true);
        selectedCatalog = catalogId;
        mDrawerLayout.openDrawer(mFragmentContainerView);
        if(currentList != null) {
            currentList.clear();
            Catalogo_ListViewAdapter adapter = new Catalogo_ListViewAdapter(currentList, this.getActivity());
            mDrawerListView.setAdapter(adapter);
        }

    }

    private void selectItem(int position) {

        Opcion actualOption = null;
        // Lets identify the actual position
        if(!isSpecialCatalog) {
            List<Opcion> listaOpciones = GetFilteredList(toFilter);
            if(listaOpciones.size() > 0) {
                actualOption = listaOpciones.get(position);
            }
        }
        else{
            actualOption = currentList.get(position);
        }


        mCurrentSelectedPosition = position;
        if (mDrawerListView != null) {
            mDrawerListView.setItemChecked(position, true);
        }
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
        if (mCallbacks != null && currentList != null) {
            //mCallbacks.onNavigationDrawerItemSelected(currentList.get(position), selectedCatalog);
            // this item is actually from the filtered list
            mCallbacks.onNavigationDrawerItemSelected(actualOption, selectedCatalog);

            //clear text search


        }
    }

    public void closeDrawer(){
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Forward the new configuration the drawer toggle component.
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // If the drawer is open, show the global app actions in the action bar. See also
        // showGlobalContextActionBar, which controls the top-left area of the action bar.
        if (mDrawerLayout != null && isDrawerOpen()) {
            inflater.inflate(R.menu.global, menu);
            showGlobalContextActionBar();
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

//        if (item.getItemId() == R.id.action_example) {
//            Toast.makeText(getActivity(), "Example action.", Toast.LENGTH_SHORT).show();
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Per the navigation drawer design guidelines, updates the action bar to show the global app
     * 'context', rather than just what's in the current screen.
     */
    private void showGlobalContextActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setTitle(R.string.app_name);
    }

    private ActionBar getActionBar() {
        return getActivity().getActionBar();
    }

    /**
     * Callbacks interface that all activities using this fragment must implement.
     */
    public static interface NavigationDrawerCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onNavigationDrawerItemSelected(Opcion opcion, int catalogId);
    }
}