package cr.bamboo.fisdigital.layout.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;

import cr.bamboo.fisdigital.layout.activity.Activity_Integrante_1;
import cr.bamboo.fisdigital.logic.global.FISController;

/**
 * Created by Jbeita on 07/06/2015.
 * Clase que permite utilizar el componente
 * de selección de fecha
 */
public class DatePicker_Fragment extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        FISController fisController = FISController.getInstance();

        final Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        if(!fisController.getIntegrante().getFechaNacimiento().equals("")) {
            String fecha = fisController.getIntegrante().getFechaNacimiento();
            String[] fechaArray = fecha.split("/");
            year = Integer.parseInt(fechaArray[2]);
            month = Integer.parseInt(fechaArray[1])-1;
            day = Integer.parseInt(fechaArray[0]);
        }

        return new DatePickerDialog(getActivity(), (Activity_Integrante_1)getActivity(), year, month, day);
    }
}
