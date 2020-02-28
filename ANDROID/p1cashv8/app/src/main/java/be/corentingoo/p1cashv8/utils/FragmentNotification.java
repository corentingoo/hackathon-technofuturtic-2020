package be.corentingoo.p1cashv8.utils;

import android.content.Context;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import be.corentingoo.p1cashv8.R;
import be.corentingoo.p1cashv8.models.Cash;

public abstract class FragmentNotification extends Fragment{
    public void notifyCurrentCapacity(int percent, Cash current, Context context){
        if (current.IdTypeCash() < 7 || current.IdTypeCash() > 11) {
            //if(current.Id()==1){
            if (percent == 100) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notification trop-plein");
                builder.setSmallIcon(R.drawable.ic_icone_machine_full_24dp);
                builder.setContentTitle("full");
                builder.setContentText(current.Type_cash() + "from machine" + current.IdMachine() + " is full");
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
                notificationManagerCompat.notify(current.Id(), builder.build());
                Log.d("MACHINE TROP PLEIN", "id " + current.Id() + "type id " + current.IdTypeCash() + " type " + current.Type_cash() + " machine " + current.IdMachine() + " current cap " + current.CurrentCapacity() + " maxcapacity " + current.MaxCapacity() + "NOTIF FULL------------------------------------------------------");
            } else if (percent > 80) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notification trop-plein");
                builder.setSmallIcon(R.drawable.ic_icone_machine_almost_full_24dp);
                builder.setContentTitle("almost full");
                builder.setContentText(current.Type_cash() + "from machine" + current.IdMachine() + " is almost full");
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
                notificationManagerCompat.notify(current.Id(), builder.build());
                Log.d("MACHINE TROP PLEIN", "id " + current.Id() + "type id " + current.IdTypeCash() + " type " + current.Type_cash() + " machine " + current.IdMachine() + " current cap " + current.CurrentCapacity() + " maxcapacity " + current.MaxCapacity() + "NOTIF almost FULL------------------------------------------------------");
            }
        } else {
            if (percent == 0) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notification reserve");
                builder.setSmallIcon(R.drawable.ic_icone_machine_empty_24dp);
                builder.setContentTitle("empty");
                builder.setContentText(current.Type_cash() + "from machine" + current.IdMachine() + " is empty");
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
                notificationManagerCompat.notify(current.Id(), builder.build());
                Log.d("MACHINE EMPTY", "type id " + current.IdTypeCash() + " type " + current.Type_cash() + " machine " + current.IdMachine() + " current cap " + current.CurrentCapacity() + "NOTIF EMPTY-------------------------------------------------");
            } else if (percent < 20) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notification reserve");
                builder.setSmallIcon(R.drawable.ic_icone_machine_almost_empty_24dp);
                builder.setContentTitle("almost empty");
                builder.setContentText(current.Type_cash() + "from machine" + current.IdMachine() + " is almost empty");
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
                notificationManagerCompat.notify(current.Id(), builder.build());
                Log.d("MACHINE EMPTY", "type id " + current.IdTypeCash() + " type " + current.Type_cash() + " machine " + current.IdMachine() + " current cap " + current.CurrentCapacity() + "NOTIF ALMOST EMPTY------------------------------------------------------");
            }
        }
    }
}
