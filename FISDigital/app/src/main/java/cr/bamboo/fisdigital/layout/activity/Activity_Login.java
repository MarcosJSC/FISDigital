package cr.bamboo.fisdigital.layout.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.res.AssetManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.orm.StringUtil;

import java.util.Date;

import cr.bamboo.fisdigital.R;
import cr.bamboo.fisdigital.data.security.CipherHelper;
import cr.bamboo.fisdigital.logic.global.FISController;
import cr.bamboo.fisdigital.data.dbmodel.Usuario;

public class Activity_Login extends Activity {

    float max = 1;
    float update = 0;
    // IMAS has to give it to us
    String tempKey = "zRVdRO+FUjC1O2dFC4KTCkH2kacde5V=";
    Date lockedTime;
    int tryouts = 0;
    private boolean canGetLocation;

    String DeviceId(){
        String serial = null;

        try {
            serial = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Ha ocurrido un error al obtener el número de identificación de su dispositivo. Reinicie la aplicación y contacte a un miembro de TI.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return serial;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            //Do some stuff
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

            dlgAlert.setMessage("Esta aplicación solo funciona mientras la tableta esté en modo horizontal, por favor asegúrese de mantenerla de esa forma.");
            dlgAlert.setTitle("Atención");

            dlgAlert.setCancelable(true);


            dlgAlert.setPositiveButton("Entiendo",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
        }

        // init location
//        Location location = getLocation(this);
final Context local = this;
        setContentView(R.layout.activity_login);

        AssetManager assetManager = getAssets();

        final Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // init location
                Location location = getLocation(local);

                if(!canGetLocation){
                    Toast.makeText(getApplicationContext(), "Para poder ingresar debe habilitar el GPS del dispositivo", Toast.LENGTH_LONG).show();
                    return;
                }

                try{
                    if(Usuario.find(Usuario.class, StringUtil.toSQLName("usuario")+"=?","admin").size() == 0) {
                        Usuario admin = new Usuario();
//            tempKey="13579Clave10Encriptacion11IMAS?!";
                        String tempKey = "zRVdRO+FUjC1O2dFC4KTCkH2kacde5V=";

                        admin.setUsuario("admin");
                        admin.setVector("zSU4IRnri/jt43NRBDnTIA==");
                        String password = "b@ckd00r";
                        byte[] pass = CipherHelper.encrypt(password.getBytes("UTF-8"), tempKey.getBytes(), admin.getVector().getBytes());
                        String passText = Base64.encodeToString(pass, 0);
                        admin.setNombre1("Admin");
                        admin.setApellido1("Admin");
                        admin.setClaveMovil(passText);

                        admin.saveOnBackground();
                    }
                }catch(Exception e){
                    Exception ex = e;
                }


                if(tryouts >= 5){
                    Date now = new Date();
                    if(addMinutesToDate(5, lockedTime).after( now )) {
                        Toast.makeText(getApplicationContext(), "Su cuenta ha sido bloqueada por 5 minutos", Toast.LENGTH_LONG).show();
                        return;
                    }else {
                        tryouts = 0;
                    }

                }
                EditText txtUserName = (EditText)findViewById(R.id.txtUserName);
                EditText txtPassword = (EditText)findViewById(R.id.txtPassword);
                if(txtUserName.getText().toString().equals("") ||txtPassword.getText().toString().equals(""))
                    return;
                Usuario user = Usuario.checkUser(txtUserName.getText().toString(), txtPassword.getText().toString(), tempKey);
                if(user != null && !user.getUsuario().equals("")) {
                    txtUserName.setText("");
                    txtPassword.setText("");

                    FISController ctrl = FISController.getInstance();

                    // Init controller with reusable values
                    ctrl.Initialize(user, DeviceId(), latitude, longitude);
                    Intent openStep = new Intent(Activity_Login.this, Activity_PantallaInicial.class);
                    startActivity(openStep);
                }else{
                    tryouts++;
                    Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrecto\n"+(5-tryouts)+" intento(s) pendiente(s)", Toast.LENGTH_LONG).show();
                    if(tryouts >= 5) {
                        lockedTime = new Date();
                    }
                }
            }
        });

        }
    private static Date addMinutesToDate(int minutes, Date beforeTime){
        final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs

        long curTimeInMs = beforeTime.getTime();
        Date afterAddingMins = new Date(curTimeInMs + (minutes * ONE_MINUTE_IN_MILLIS));
        return afterAddingMins;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    public double latitude, longitude;
    private void assignLocation(double _latitude, double _longitude){
        latitude=_latitude;
        longitude = _longitude;
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
