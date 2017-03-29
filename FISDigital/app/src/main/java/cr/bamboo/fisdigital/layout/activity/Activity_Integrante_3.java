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

public class Activity_Integrante_3 extends FISActivity
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
               setContentView(R.layout.activity_integrante_3);

               mNavigationDrawerFragmentCara1 = (NavigationDrawer_Fragment)
                       getFragmentManager().findFragmentById(R.id.navigation_drawer);

               mDashboardDrawerFragmentCara1 = (DashboardDrawer_Fragment)
                       getFragmentManager().findFragmentById(R.id.dashboard_drawer1);

          mDashboardDrawerFragmentCara1.getArguments().putString("textoSecciones", "7/10");
          mDashboardDrawerFragmentCara1.getArguments().putString("textoPreguntas", "50/61");
          mDashboardDrawerFragmentCara1.setData();

               mTitle = getString(R.string.title_seccion3);

               // Set up the drawer.
               mNavigationDrawerFragmentCara1.setUp(
                       R.id.navigation_drawer,
                       (DrawerLayout) findViewById(R.id.drawer_layout7));

          mDashboardDrawerFragmentCara1.setUp(R.id.dashboard_drawer1, (DrawerLayout) findViewById(R.id.drawer_layout7));

               FragmentManager fragmentManager = getFragmentManager();
               globalFragment =  PlaceholderFragment7.newInstance(1);
               fragmentManager.beginTransaction()
                       .replace(R.id.container7, globalFragment)
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

          //Deficiencia Fisica
          if(catalogId == 0) {
              setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvDeficiencia));
              fisController.getIntegrante().setDeficienciaFisica(opcion);
          }

          //Enfermedad Cronica
          if(catalogId == 1) {
              setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvEnfermedadCronica));
              fisController.getIntegrante().setEnfermedadCronica(opcion);
          }

          //Aspectos Psicosociales
          if(catalogId == 2) {
              setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvAspectosPsicosociales));
              fisController.getIntegrante().setAspectosPsicosociales(opcion);
          }

          //Tipo de Pension
          if(catalogId == 3) {
              setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvTipoPension));
              fisController.getIntegrante().setTipoPension(opcion);
          }

          //Condicion Aseguramiento
          if(catalogId == 4) {
              setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvCondicionAseguramiento));
              fisController.getIntegrante().setCondicionAseguramiento(opcion);
          }

          //Asistencia Centros Enseñanza
          if(catalogId == 5) {
              setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvAsistenciaCentros));
              fisController.getIntegrante().setAsistenciaCentrosEnsenanza(opcion);
              if(fisController.getIntegrante().getIdAsistenciaCentrosEnsenanza().equals("3")){
                  ((Button) globalFragment.getView().findViewById(R.id.btnCentroEnsenanza)).setEnabled(false);
                  ((TextView) globalFragment.getView().findViewById(R.id.tvCentroEnsenanza)).setText("No Aplica");
              }else {
                  ((Button) globalFragment.getView().findViewById(R.id.btnCentroEnsenanza)).setEnabled(true);
                  ((TextView) globalFragment.getView().findViewById(R.id.tvCentroEnsenanza)).setText("");

              }
          }

          //Ciclo Enseñanza
          if(catalogId == 6) {
              setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvCicloEnsenanza));
              fisController.getIntegrante().setCicloEnsenanza(opcion);

              if(Integer.parseInt(fisController.getIntegrante().getIdCicloEnsenanza()) <=3){
                  fisController.getIntegrante().setUltimoAnoAprobado(0);
                  ((EditText) globalFragment.getView().findViewById(R.id.etUltimoAnoAprobado)).setText("0");
                  ((EditText) globalFragment.getView().findViewById(R.id.etUltimoAnoAprobado)).setEnabled(false);
              }
             else{
                  ((EditText) globalFragment.getView().findViewById(R.id.etUltimoAnoAprobado)).setEnabled(true);
              }
          }

          //Area de Capacitacion
          if(catalogId == 7) {
              setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvAreaCapacitacion));
              fisController.getIntegrante().setAreaCapacitacion(opcion);
          }

          //Asistencia Centros Enseñanza
          if(catalogId == 8) {
              setText(opcion.getDescripcion(), (TextView) globalFragment.getView().findViewById(R.id.tvCentroEnsenanza));
              fisController.getIntegrante().setCodigoCentroEnsenanza(opcion);
          }
      }

      public PlaceholderFragment7 globalFragment;
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
             public static class PlaceholderFragment7 extends Fragment {
                 /**
                  * The fragment argument representing the section number for this
                  * fragment.
                  */
                 private static final String ARG_SECTION_NUMBER = "section_number";
                 View globalRootView;
                 ScrollView sv;

                 FISController fisController = FISController.getInstance();
                 String[] errorMessages = new String[]{
                         "Debe seleccionar una opción para Deficiencia.",
                         "Debe seleccionar una opción para Enfermedad Crónica o Terminal.",
                         "Debe seleccionar una opción para Aspectos Psicosociales.",
                         "Debe seleccionar una opción para Tipo de Pensión.",
                         "Debe seleccionar una opción para Condición de Aseguramiento.",
                         "Debe seleccionar una opción para Asistencia a Centros de Enseñanza",
                         "Debe seleccionar una opción para Tipo de Pensión.",
                         "El Último Año Aprobado no puede estar en blanco.",
                         "Debe seleccionar una opción para Ciclo de Enseñanza.",
                         "Debe seleccionar una opción para Area de Capacitación.",
                         "El Último Año Aprobado no coincide con el Ciclo de Enseñanza.",
                         "El Centro de Enseñanza no puede estar en blanco."
                 };

                 public static PlaceholderFragment7 newInstance(int sectionNumber) {
                     PlaceholderFragment7 fragment = new PlaceholderFragment7();
                     Bundle args = new Bundle();
                     args.putInt(ARG_SECTION_NUMBER, sectionNumber);
                     fragment.setArguments(args);
                     return fragment;
                 }

                 public PlaceholderFragment7() {
                 }

                 Activity_Integrante_3 parentActivity;
                 @Override
                 public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                          Bundle savedInstanceState) {
                     parentActivity = ((Activity_Integrante_3) getActivity());

                     globalRootView= inflater.inflate(R.layout.fragment_integrante_3, container, false);
                     Init(globalRootView);
                     return globalRootView;
                 }

                 void Init(final View v){
                     sv = (ScrollView)v.findViewById(R.id.svGeneralCara7);

                     if (fisController.idIntegranteEditar != 0) {
                         try {

                             ((TextView) v.findViewById(R.id.tvDeficiencia)).setText(fisController.getIntegrante().getDeficienciaFisica());
                             ((TextView) v.findViewById(R.id.tvEnfermedadCronica)).setText(fisController.getIntegrante().getEnfermedadCronica());
                             ((TextView) v.findViewById(R.id.tvAspectosPsicosociales)).setText(fisController.getIntegrante().getAspectosPsicosociales());
                             ((TextView) v.findViewById(R.id.tvTipoPension)).setText(fisController.getIntegrante().getTipoPension());
                             ((TextView) v.findViewById(R.id.tvCondicionAseguramiento)).setText(fisController.getIntegrante().getCondicionAseguramiento());
                             ((EditText) v.findViewById(R.id.etUltimoAnoAprobado)).setText(""+fisController.getIntegrante().getUltimoAnoAprobado());
                             ((TextView) v.findViewById(R.id.tvCentroEnsenanza)).setText(fisController.getIntegrante().getCodigoCentroEnsenanza());
                             ((TextView) v.findViewById(R.id.tvAreaCapacitacion)).setText(fisController.getIntegrante().getAreaCapacitacion());


                             if (!fisController.getIntegrante().getIdAsistenciaCentrosEnsenanza().isEmpty()) {
                                 ((TextView) v.findViewById(R.id.tvAsistenciaCentros)).setText(fisController.getIntegrante().getAsistenciaCentrosEnsenanza());
                                 if (fisController.getIntegrante().getIdAsistenciaCentrosEnsenanza().equals("3")) {
                                     ((Button) v.findViewById(R.id.btnCentroEnsenanza)).setEnabled(false);
                                     ((TextView) v.findViewById(R.id.tvCentroEnsenanza)).setText("No Aplica");
                                 } else {
                                     ((Button) v.findViewById(R.id.btnCentroEnsenanza)).setEnabled(true);
                                 }
                             }

                             if (fisController.getIntegrante().getEdad() < 5) {
                                 ((EditText) v.findViewById(R.id.etUltimoAnoAprobado)).setText("0");
                                 ((EditText) v.findViewById(R.id.etUltimoAnoAprobado)).setEnabled(false);
                                 ((TextView) v.findViewById(R.id.tvAsistenciaCentros)).setText("No aplica.");
                                 ((TextView) v.findViewById(R.id.tvAsistenciaCentros)).setEnabled(false);
                                 ((TextView) v.findViewById(R.id.tvCentroEnsenanza)).setText("No aplica.");
                                 ((TextView) v.findViewById(R.id.tvCentroEnsenanza)).setEnabled(false);
                                 ((TextView) v.findViewById(R.id.tvCicloEnsenanza)).setText("No aplica.");
                                 ((TextView) v.findViewById(R.id.tvCicloEnsenanza)).setEnabled(false);
                             } else {
                                 ((EditText) v.findViewById(R.id.etUltimoAnoAprobado)).setText(""+fisController.getIntegrante().getUltimoAnoAprobado());
                                 ((EditText) v.findViewById(R.id.etUltimoAnoAprobado)).setEnabled(true);
                             }

                             if (!fisController.getIntegrante().getIdCicloEnsenanza().isEmpty()) {
                                 ((TextView) v.findViewById(R.id.tvCicloEnsenanza)).setText(fisController.getIntegrante().getCicloEnsenanza());
                                 if (Integer.parseInt(fisController.getIntegrante().getIdCicloEnsenanza()) <= 3) {
                                     ((EditText) v.findViewById(R.id.etUltimoAnoAprobado)).setText("0");
                                     ((EditText) v.findViewById(R.id.etUltimoAnoAprobado)).setEnabled(false);
                                 } else {
                                     ((EditText) v.findViewById(R.id.etUltimoAnoAprobado)).setEnabled(true);
                                     ((EditText) v.findViewById(R.id.etUltimoAnoAprobado)).setText(""+fisController.getIntegrante().getUltimoAnoAprobado());
                                 }
                             }

                             if (!fisController.getIntegrante().getIdCondicionActividad().equals("7")) {
                                 ((TextView) v.findViewById(R.id.tvTipoPension)).setText("No aplica.");
                                 ((Button) v.findViewById(R.id.btnTipoPension)).setEnabled(false);
                                 fisController.getIntegrante().setIdTipoPension("0");
                             } else {
                                 ((TextView) v.findViewById(R.id.tvTipoPension)).setText(fisController.getIntegrante().getTipoPension());
                                 ((Button) v.findViewById(R.id.btnTipoPension)).setEnabled(true);
                             }

                             if (fisController.getIntegrante().getEdad() < 15) {
                                 ((TextView) v.findViewById(R.id.tvAreaCapacitacion)).setText("No Aplica.");
                                 ((Button) v.findViewById(R.id.btnAreaCapacitacion)).setEnabled(false);
                             } else {
                                 ((TextView) v.findViewById(R.id.tvAreaCapacitacion)).setText(fisController.getIntegrante().getAreaCapacitacion());
                                 ((Button) v.findViewById(R.id.btnAreaCapacitacion)).setEnabled(true);
                             }

                         } catch (Exception ex) {
                             ex.printStackTrace();
                         }
                     }
                     else {

                         if (!fisController.getIntegrante().getIdCondicionActividad().equals("7")) {
                             ((TextView) v.findViewById(R.id.tvTipoPension)).setText("No aplica.");
                             ((Button) v.findViewById(R.id.btnTipoPension)).setEnabled(false);
                             fisController.getIntegrante().setIdTipoPension("0");
                         } else {
                             ((TextView) v.findViewById(R.id.tvTipoPension)).setText("");
                             ((Button) v.findViewById(R.id.btnTipoPension)).setEnabled(true);
                             fisController.getIntegrante().setIdTipoPension("");
                         }

                         if (fisController.getIntegrante().getEdad() < 5) {
                             ((EditText) v.findViewById(R.id.etUltimoAnoAprobado)).setText("0");
                             ((EditText) v.findViewById(R.id.etUltimoAnoAprobado)).setEnabled(false);
                             fisController.getIntegrante().setUltimoAnoAprobado(0);
                             ((TextView) v.findViewById(R.id.tvAsistenciaCentros)).setText("No aplica.");
                             ((TextView) v.findViewById(R.id.tvAsistenciaCentros)).setEnabled(false);
                             fisController.getIntegrante().setIdAsistenciaCentrosEnsenanza("");
                             ((TextView) v.findViewById(R.id.tvCentroEnsenanza)).setText("No aplica.");
                             ((TextView) v.findViewById(R.id.tvCentroEnsenanza)).setEnabled(false);
                             fisController.getIntegrante().setIdCodigoCentroEnsenanza("");
                             ((TextView) v.findViewById(R.id.tvCicloEnsenanza)).setText("No aplica.");
                             ((TextView) v.findViewById(R.id.tvCicloEnsenanza)).setEnabled(false);
                             fisController.getIntegrante().setIdCicloEnsenanza("");
                         } else {
                             ((EditText) v.findViewById(R.id.etUltimoAnoAprobado)).setText("");
                             ((EditText) v.findViewById(R.id.etUltimoAnoAprobado)).setEnabled(true);
                             fisController.getIntegrante().setUltimoAnoAprobado(0);
                         }

                         if (fisController.getIntegrante().getEdad() < 15) {
                             ((TextView) v.findViewById(R.id.tvAreaCapacitacion)).setText("No Aplica.");
                             ((Button) v.findViewById(R.id.btnAreaCapacitacion)).setEnabled(false);
                             fisController.getIntegrante().setIdAreaCapacitacion("0");
                         } else {
                             ((TextView) v.findViewById(R.id.tvAreaCapacitacion)).setText("");
                             ((Button) v.findViewById(R.id.btnAreaCapacitacion)).setEnabled(true);
                             fisController.getIntegrante().setIdAreaCapacitacion("");
                         }
                     }

                     v.findViewById(R.id.btnDeficiencia).setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View vx) {
                             Activity_Integrante_3 localAct = ((Activity_Integrante_3) getActivity());
                             localAct.mNavigationDrawerFragmentCara1.Open(Integrante.getOpcionesDeficiencia(), 0);
                             parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};
                         }
                     });

                     v.findViewById(R.id.btnEnfermedadCronica).setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View vx) {
                             Activity_Integrante_3 localAct = ((Activity_Integrante_3) getActivity());
                             localAct.mNavigationDrawerFragmentCara1.Open(Integrante.getOpcionesEnfermedadCronica(), 1);
                             parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};
                         }
                     });

                     v.findViewById(R.id.btnAspectosPsicosociales).setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View vx) {
                             Activity_Integrante_3 localAct = ((Activity_Integrante_3) getActivity());
                             localAct.mNavigationDrawerFragmentCara1.Open(Integrante.getOpcionesAspectosPsicosociales(), 2);
                             parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};
                         }
                     });

                     v.findViewById(R.id.btnTipoPension).setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View vx) {
                             Activity_Integrante_3 localAct = ((Activity_Integrante_3) getActivity());
                             localAct.mNavigationDrawerFragmentCara1.Open(Integrante.getOpcionesTipoPension(), 3);
                             parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};
                         }
                     });

                     v.findViewById(R.id.btnCondicionAseguramiento).setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View vx) {
                             Activity_Integrante_3 localAct = ((Activity_Integrante_3) getActivity());
                             localAct.mNavigationDrawerFragmentCara1.Open(Integrante.getOpcionesCondicionAseguramiento(), 4);
                             parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};
                         }
                     });

                     v.findViewById(R.id.btnAsistenciaCentros).setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View vx) {
                             Activity_Integrante_3 localAct = ((Activity_Integrante_3) getActivity());
                             localAct.mNavigationDrawerFragmentCara1.Open(Integrante.getOpcionesAsistenciaCentros(fisController.getIntegrante().getIdCondicionActividad()), 5);
                             parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};
                         }
                     });

                     v.findViewById(R.id.btnCicloEnsenanza).setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View vx) {
                             Activity_Integrante_3 localAct = ((Activity_Integrante_3) getActivity());
                             localAct.mNavigationDrawerFragmentCara1.Open(Integrante.getOpcionesCicloEnsenanza(), 6);
                             parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};
                         }
                     });

                     v.findViewById(R.id.btnAreaCapacitacion).setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View vx) {
                             Activity_Integrante_3 localAct = ((Activity_Integrante_3) getActivity());
                             localAct.mNavigationDrawerFragmentCara1.Open(Integrante.getOpcionesAreaCapacitacion(), 7);
                             parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};
                         }
                     });

                     v.findViewById(R.id.btnCentroEnsenanza).setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View vx) {
                             Activity_Integrante_3 localAct = ((Activity_Integrante_3) getActivity());
                             localAct.mNavigationDrawerFragmentCara1.Open(8);
                             parentActivity.memoryScrollPos =new int[] {sv.getScrollX(), sv.getScrollY()};
                         }
                     });

                     v.findViewById(R.id.btnSiguiente).setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View vx) {

                             if(!formIsValid(v)){
                                 ScrollView sv = (ScrollView)v.findViewById(R.id.svGeneralCara7);
                                 sv.scrollTo(0, 0);
                                 ((LinearLayout)v.findViewById(R.id.errorMsg7)).setVisibility(View.VISIBLE);
                             }
                             else {
                                 parentActivity.mDashboardDrawerFragmentCara1.saveObservaciones();
                                 fisController.saveIntegrante();
                                 Intent openStep = new Intent(parentActivity, Activity_ListarIntegrantes.class);
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

                     tv = ((TextView) v.findViewById(R.id.tvDeficiencia));
                     if (fisController.getIntegrante().getIdDeficienciaFisica().equals("")) {
                         setError(0, tv);
                         error = true;
                     }

                     tv = ((TextView) v.findViewById(R.id.tvEnfermedadCronica));
                     if (fisController.getIntegrante().getIdEnfermedadCronica().equals("")) {
                         setError(1, tv);
                         error = true;
                     }

                     tv = ((TextView) v.findViewById(R.id.tvAspectosPsicosociales));
                     if (fisController.getIntegrante().getIdAspectosPsicosociales().equals("")) {
                         setError(2, tv);
                         error = true;
                     }

                     tv = ((TextView) v.findViewById(R.id.tvTipoPension));
                     if (fisController.getIntegrante().getIdTipoPension().equals("")) {
                         setError(3, tv);
                         error = true;
                     }

                     tv = ((TextView) v.findViewById(R.id.tvCondicionAseguramiento));
                     if (fisController.getIntegrante().getIdCondicionAseguramiento().equals("")) {
                         setError(4, tv);
                         error = true;
                     }

                     tv = ((TextView) v.findViewById(R.id.tvAsistenciaCentros));
                     if (fisController.getIntegrante().getIdAsistenciaCentrosEnsenanza().equals("") && fisController.getIntegrante().getEdad() >= 5) {
                         setError(5, tv);
                         error = true;
                     }

                     et = ((EditText) v.findViewById(R.id.etUltimoAnoAprobado));
                     String idCicloEnsenanzaStr = fisController.getIntegrante().getIdCicloEnsenanza();
                     if (et.getText().toString().trim().equals("")) {
                         setError(7, et);
                         error = true;
                     } else {
                         if (!idCicloEnsenanzaStr.isEmpty()) {
                             int idCicloEnsenanza = Integer.parseInt(idCicloEnsenanzaStr);
                             int ultimoAno = Integer.parseInt(et.getText().toString());
                             if (idCicloEnsenanza == 4 && (ultimoAno < 1 || ultimoAno > 6)) {
                                 setError(10, et);
                                 error = true;
                             } else if (idCicloEnsenanza == 5 && (ultimoAno < 7 || ultimoAno > 11)) {
                                 setError(10, et);
                                 error = true;
                             } else if (idCicloEnsenanza == 6 && (ultimoAno < 7 || ultimoAno > 12)) {
                                 setError(10, et);
                                 error = true;
                             } else if (idCicloEnsenanza > 6 && (ultimoAno < 12 || ultimoAno > 20)) {
                                 setError(10, et);
                                 error = true;
                             } else {
                                 et.setError(null);
                                 fisController.getIntegrante().setUltimoAnoAprobado(Integer.parseInt(et.getText().toString()));
                             }
                         }
                     }


                     tv = ((TextView) v.findViewById(R.id.tvCicloEnsenanza));
                     if (fisController.getIntegrante().getIdCicloEnsenanza().equals("") && fisController.getIntegrante().getEdad() >= 5) {
                         setError(8, tv);
                         error = true;
                     }

                     tv = ((TextView) v.findViewById(R.id.tvAreaCapacitacion));
                     if (fisController.getIntegrante().getIdAreaCapacitacion().equals("") && fisController.getIntegrante().getEdad() >= 5) {
                         setError(9, tv);
                         error = true;
                     }

                     tv = ((TextView) v.findViewById(R.id.tvCentroEnsenanza));
                     if (fisController.getIntegrante().getIdCodigoCentroEnsenanza().equals("")) {
                         if (!fisController.getIntegrante().getIdCicloEnsenanza().isEmpty()) {
                             int idCicloEnsenanza = Integer.parseInt(fisController.getIntegrante().getIdCicloEnsenanza());
                             if (idCicloEnsenanza >= 4 && idCicloEnsenanza <= 7) {
                                 setError(11, tv);
                                 error = true;
                             }
                         }
                     }

                     return !error;
                 }

                 @Override
                 public void onAttach(Activity activity) {
                     super.onAttach(activity);

                     ((Activity_Integrante_3) activity).onSectionAttached(
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