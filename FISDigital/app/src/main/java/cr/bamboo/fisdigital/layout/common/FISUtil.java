package cr.bamboo.fisdigital.layout.common;

import android.widget.TextView;

import cr.bamboo.fisdigital.layout.activity.Activity_Ubicacion;
import cr.bamboo.fisdigital.R;

/**
 * Created by Jose Beita on 03/10/2015.
 * La clase FISUtil contiene metodos que se utilizan a lo largo de la aplicación
 */
public class FISUtil {

    public static void setText(Activity_Ubicacion.PlaceholderFragment globalFragment, String description, TextView tv){
        tv.setText(description);
        tv.setTextAppearance(globalFragment.getView().getContext(), R.style.FISTextView);
        tv.setBackgroundResource(R.drawable.textview_border);
    }
}
