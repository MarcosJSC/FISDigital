package cr.bamboo.fisdigital.data.ftp.common;

/**
 * Created by Jose Beita on 31/05/2015.
 * Interface para comunicar las tareas asíncronas con el hilo principal
 */
public interface OnTaskListener {
    public void TaskDone(String message);
    public void setProgressPercent(String... catalogo);
}
