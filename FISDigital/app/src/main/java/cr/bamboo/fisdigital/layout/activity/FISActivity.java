package cr.bamboo.fisdigital.layout.activity;

import android.app.Activity;
import android.content.Intent;

import java.util.Calendar;

import cr.bamboo.fisdigital.R;
import cr.bamboo.fisdigital.layout.fragment.NavigationDrawer_Fragment;
import cr.bamboo.fisdigital.layout.fragment.DashboardDrawer_Fragment;

/**
 * Created by walter on 21/06/15.
 * Clase Padre de las actividades que controla principalmente
 * el manejo del botón atraás del dispositivo dependiendo de
 * la pantalla en donde se encuentre el usuario
 *
 * La mayoría de las actividades estan divididas en 4 componentes
 *
 * 1. Activity principal: Pantalla contenedora principal
 * 2. Fragment: Contenedor del formulario
 * 3. DashboardDrawer: Contenedor del submenú izquierdo
 * 4. NavigationDrawer: Contenedor de los catálogos
 *
 * En estas clases se encuentra la validación de datos y la
 * interaccion con la interfaz de usuario
 *
 * Algunas pantallas más simples pueden contener sólo algunos de estos
 * elementos
 */
public class FISActivity extends Activity {
    Calendar goBack = null;

    int lookback;


    public void lookBackButton(int screen){
        lookback=screen;
    }
    @Override
    public void onBackPressed() {

    DashboardDrawer_Fragment dashboardDrawer = ((DashboardDrawer_Fragment) this.getFragmentManager().findFragmentById(R.id.dashboard_drawer1));
    NavigationDrawer_Fragment navigationDrawer = ((NavigationDrawer_Fragment) this.getFragmentManager().findFragmentById(R.id.navigation_drawer));

        if(dashboardDrawer != null && dashboardDrawer.isDrawerOpen()){

            dashboardDrawer.closeDrawer();
        }

        else if(navigationDrawer != null && navigationDrawer.isDrawerOpen()){
            navigationDrawer.closeDrawer();
        }
        else {
            //1 Inicial
            if (lookback == 1) {
                Intent openStep = new Intent(getApplicationContext(), Activity_PantallaInicial.class);
                startActivity(openStep);
                return;
            }
            //2 Listar
            else if (lookback == 2) {
                Intent openStep = new Intent(getApplicationContext(), Activity_ListarFIS.class);
                startActivity(openStep);
                return;
            }
            //3 Familias
            else if (lookback == 3) {
                Intent openStep = new Intent(getApplicationContext(), Activity_ListarFamilias.class);
                startActivity(openStep);
                return;
            }
            else{
                super.onBackPressed();
            }
        }
    }

}
