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
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
 import android.widget.TextView;

 import java.util.ArrayList;
 import java.util.List;

import cr.bamboo.fisdigital.R;
import cr.bamboo.fisdigital.layout.fragment.DashboardDrawer_Fragment;
import cr.bamboo.fisdigital.layout.adapter.ListarCodigos_ListViewAdapater;
import cr.bamboo.fisdigital.layout.fragment.NavigationDrawer_Fragment;
import cr.bamboo.fisdigital.logic.bo.Codigo;
import cr.bamboo.fisdigital.logic.global.FISController;
import cr.bamboo.fisdigital.data.dbmodel.Opcion;

public class Activity_CodigosEspeciales extends FISActivity
        implements NavigationDrawer_Fragment.NavigationDrawerCallbacks {

      private CharSequence mTitle;
      FISController fisController = FISController.getInstance();

    private NavigationDrawer_Fragment mNavigationDrawerFragmentCara1;
    private DashboardDrawer_Fragment mDashboardDrawerFragmentCara1;

      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_codigos_especiales);
          this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

          mNavigationDrawerFragmentCara1 = (NavigationDrawer_Fragment)
                  getFragmentManager().findFragmentById(R.id.navigation_drawer);

          mDashboardDrawerFragmentCara1 = (DashboardDrawer_Fragment)
                  getFragmentManager().findFragmentById(R.id.dashboard_drawer1);

          mDashboardDrawerFragmentCara1.getArguments().putString("textoSecciones", "10/10");
          mDashboardDrawerFragmentCara1.getArguments().putString("textoPreguntas", "60/61");
          mDashboardDrawerFragmentCara1.setData();

          mNavigationDrawerFragmentCara1.setUp(
                  R.id.navigation_drawer,
                  (DrawerLayout) findViewById(R.id.drawer_layout9));

          mDashboardDrawerFragmentCara1.setUp(R.id.dashboard_drawer1, (DrawerLayout) findViewById(R.id.drawer_layout9));

          mTitle = "Códigos Especiales";
          restoreActionBar();

          FragmentManager fragmentManager = getFragmentManager();
          globalFragment =  PlaceholderFragment9.newInstance(1);
          fragmentManager.beginTransaction()
                  .replace(R.id.container9, globalFragment)
                  .commit();
      }

    @Override
    public void onNavigationDrawerItemSelected(Opcion opcion, int catalogId) {
    }

   public void loadData(){
        globalFragment.loadData();
   }

    public void updateBusqueda(Codigo codigo){
        if(globalFragment != null) {
            globalFragment.updateUIBusqueda(codigo);
        }
    }

    public void updateBusqueda(){
        if(globalFragment != null) {
            globalFragment.updateUIBusqueda();
        }
    }

      private void setText(String description, TextView tv){
          tv.setText(description);
          tv.setTextAppearance(globalFragment.getView().getContext(), R.style.FISTextView);
          tv.setBackgroundResource(R.drawable.textview_border);
      }

      public PlaceholderFragment9 globalFragment;
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
          return super.onCreateOptionsMenu(menu);
      }

      @Override
      public boolean onOptionsItemSelected(MenuItem item) {
          int id = item.getItemId();

          if (id == R.id.action_settings) {
              return true;
          }

          return super.onOptionsItemSelected(item);
      }

      /**
       * A placeholder fragment containing a simple view.
       */
      public static class PlaceholderFragment9 extends Fragment {
          /**
           * The fragment argument representing the section number for this
           * fragment.
           */
          private static final String ARG_SECTION_NUMBER = "section_number";
          FISController fisController = FISController.getInstance();

          View globalRootView;
          ScrollView sv;

          // To validate in there are errors in the form
          boolean areErrorsInForm = false;


          public static PlaceholderFragment9 newInstance(int sectionNumber) {
              PlaceholderFragment9 fragment = new PlaceholderFragment9();
              Bundle args = new Bundle();
              args.putInt(ARG_SECTION_NUMBER, sectionNumber);
              fragment.setArguments(args);
              return fragment;
          }

          public PlaceholderFragment9() {
          }

          Activity_CodigosEspeciales parentActivity;
          List<Opcion> codigos= null;
          @Override
          public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                   Bundle savedInstanceState) {
              parentActivity = ((Activity_CodigosEspeciales) getActivity());

              globalRootView= inflater.inflate(R.layout.fragment_codigos_especiales, container, false);
              Init(globalRootView);
              return globalRootView;
          }
          ListarCodigos_ListViewAdapater adapter;
          ListView resultados;
          ArrayList agregados;


          public void loadData(){
              agregados = fisController.getCodigosPorFamilia();
              ListarCodigos_ListViewAdapater adapter = new ListarCodigos_ListViewAdapater(agregados, globalRootView.getContext(), true);
              ListView resultados = ((ListView) globalRootView.findViewById(R.id.codigosEspecialesListView));
              resultados.setAdapter(adapter);

//              if (agregados.size() == 0) {
//                  globalRootView.findViewById(R.id.tvCodigosAgregados).setVisibility(View.INVISIBLE);
//              }
//              else{
//                  globalRootView.findViewById(R.id.tvCodigosAgregados).setVisibility(View.VISIBLE);
//              }

              TextView emptyText = (TextView) globalRootView.findViewById(android.R.id.empty);
              resultados.setEmptyView(emptyText);
          }


          void Init(final View v){
              sv = (ScrollView)v.findViewById(R.id.svGeneralCara9);
              v.findViewById(R.id.btnSiguiente).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View vx) {
                          parentActivity.mDashboardDrawerFragmentCara1.saveObservaciones();
                          Intent openStep = new Intent(parentActivity, Activity_ListarFamilias.class);
                          startActivity(openStep);
                  }
              });

              loadData();

              sv.post(new Runnable(){
                  public void run(){
                      sv.scrollTo(parentActivity.memoryScrollPos[0], parentActivity.memoryScrollPos[1]);
                  }
              });

              v.findViewById(R.id.btn_buscar_codigos).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View vx) {
                      String codigo = ((EditText) v.findViewById(R.id.etBusquedaCodigo)).getText().toString().trim();
                      String descripcion = ((EditText) v.findViewById(R.id.etBusquedaDesc)).getText().toString().trim();
                      v.clearFocus();

                      View view = parentActivity.getCurrentFocus();
                      if (view != null) {
                          InputMethodManager imm = (InputMethodManager)globalRootView.getContext().getSystemService(globalRootView.getContext().INPUT_METHOD_SERVICE);
                          imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                      }

                      ArrayList<Codigo> codigos = fisController.getCodigosEspeciales(codigo, descripcion);
                      adapter = new ListarCodigos_ListViewAdapater(codigos, globalRootView.getContext(), false);
                      resultados = ((ListView) v.findViewById(R.id.codigosEspecialesListViewResultados));
                      resultados.setAdapter(adapter);

                      TextView emptyText = (TextView) v.findViewById(R.id.empty2);
                      if(codigos.size() == 0) {
                          emptyText.setVisibility(View.VISIBLE);
                          resultados.setEmptyView(emptyText);
                      }
                      else{
                          emptyText.setVisibility(View.GONE);
                      }

                  }
              });

          }

          public void updateUIBusqueda() {
              resultados.deferNotifyDataSetChanged();
              adapter.notifyDataSetChanged();
          }
          public void updateUIBusqueda(Codigo codigo) {
              if(adapter == null){
                  adapter = new ListarCodigos_ListViewAdapater(new ArrayList<Codigo>(), getActivity(), false);
                  resultados = ((ListView) globalRootView.findViewById(R.id.codigosEspecialesListViewResultados));
                  resultados.setAdapter(adapter);
              }
              adapter.addItem(codigo);
              resultados.deferNotifyDataSetChanged();
              adapter.notifyDataSetChanged();
          }

          @Override
          public void onAttach(Activity activity) {
              super.onAttach(activity);

              ((Activity_CodigosEspeciales) activity).onSectionAttached(
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