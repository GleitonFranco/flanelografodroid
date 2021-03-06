package br.edu.fanor;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class HelloAndroidActivity extends Activity {
    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after 
     * previously being shut down then this Bundle contains the data it most 
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
	private static final String OPERATION = "consultaSala";
	private static final String NAMESPACE = "http://localhost:8180/SoapTest/services/teste";
	private static final String SITE = "http://192.168.0.128:8180/SoapTest/TesteService?wsdl";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String s = "";
        try {
        	SoapObject envio = new SoapObject(NAMESPACE,OPERATION);
        	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        	envio.addProperty("curso","ADS");
        	envelope.setOutputSoapObject(envio);
        	HttpTransportSE androidHttpTransport = new HttpTransportSE(SITE);
        	androidHttpTransport.call("\""+OPERATION+"\"", envelope);
        	SoapObject resultado = (SoapObject) envelope.bodyIn;
        	new AlertDialog.Builder(this).setTitle("Resultado WebService").setMessage(resultado.getProperty(0).toString())
        	.setNeutralButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dlg, int sumthin) {
					
				}
			}).show();
        } catch(Exception e) { 
        	Log.e("log",e.getMessage()); 
        	s=s+" ---"+e.getMessage();
        	new AlertDialog.Builder(this).setTitle("Resultado teste").setMessage(s)
        	.setNeutralButton("OK", new DialogInterface.OnClickListener() {
        		public void onClick(DialogInterface dlg, int sumthin) {
        			
        		}
        	}).show();
        	
        }
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
    }
    
    private List<CharSequence> montaListaCursos() {
		List<CharSequence> listaRetorno = new ArrayList<CharSequence>();
		
		return listaRetorno;
	}

}

