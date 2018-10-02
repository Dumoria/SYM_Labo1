/*
 * File     : MainActivity.java
 * Project  : TemplateActivity
 * Author   : Markus Jaton 2 juillet 2014
 * 			  Fabien Dutoit 28 août 2018
 *            IICT / HEIG-VD
 *                                       
 * mailto:fabien.dutoit@heig-vd.ch
 * 
 * This piece of code reads a [email_account / password ] combination.
 * It is used as a template project for the SYM module element given at HEIG-VD
 * Target audience : students IL, TS, IE [generally semester 1, third bachelor year]
 *   
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY 
 * AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL 
 * THE REGENTS OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED 
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package ch.heigvd.sym.template;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // For logging purposes
    private static final String TAG = MainActivity.class.getSimpleName();

	//initialisation du tableaux des couples mail/mot de passe
   private Pair [] couples={
            new Pair("oussama@gmail.com","oussama"),
            new Pair("benjamin@gmail.com","benjamin"),
            new Pair("simon@gmail.com","siomn"),
            new Pair("toto@tutu.com","tata")};
   
    // GUI elements
	private EditText email      = null;
    private Button   signIn     = null;
	private EditText mdp      = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		// Show the welcome screen / login authentication dialog
		setContentView(R.layout.authent);

		// Link to GUI elements
        this.email      = findViewById(R.id.email);
        this.mdp		=findViewById(R.id.mdp);
        this.signIn     = findViewById(R.id.buttOk);

		// Then program action associated to "Ok" button
		signIn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				/*
				 * There you have to check out if the email/password
				 * combination given is valid or not
				 */
				String mail = email.getText().toString();
				String passwd = mdp.getText().toString();

				//test if we have the @ in the email
                //if we don't have we show a Toast
				if(!mail.contains("@")){
					Context context = getApplicationContext();
					CharSequence text = "email sans @ !";
					int duration = Toast.LENGTH_SHORT;

					Toast toastEmail = Toast.makeText(context, text, duration);
					toastEmail.show();
					return;
				}
				if (isValid(mail, passwd)) {

					/* Ok, valid combination, do something or launch another activity...
					 * The current activity could be finished, but it is not mandatory.
					 * To launch activity MyActivity.class, try something like :
					 * 
					 * 			Intent intent = new Intent(this, ch.heigvd.sym.MyActivity.class);
					 * 			intent.putExtra("emailEntered", mail);
					 *			intent.putExtra("passwordGiven", passwd);
					 *			this.startActivity(intent); 
					 *
					 * Alternately, you could also startActivityForResult if you are awaiting a result.
					 * In the latter case, you have to indicate an int parameter to identify MyActivity
					 * 
					 * If you haven't anything more to do, you may finish()...
					 * But just display a small message before quitting...
					 */
					Toast.makeText(MainActivity.this, getResources().getString(R.string.good), Toast.LENGTH_LONG).show();
					finish();
				} else {
					// Wrong combination, display pop-up dialog and stay on login screen
					showErrorDialog(mail, passwd);
				}

			}
			
		});
	}
	
	private boolean isValid(String mail, String passwd) {

        if(mail == null || passwd == null) {
            Log.w(TAG, "isValid(mail, passwd) - mail and passwd cannot be null !");
            return false;
        }
		// Return true if combination valid, false otherwise
		return (mail.equals(validEmail) && passwd.equals(validPassword));
	}
	
	protected void showErrorDialog(String mail, String passwd) {
		email.setText("");
		mdp.setText("");
		AlertDialog.Builder alertbd = new AlertDialog.Builder(this);
        alertbd.setIcon(android.R.drawable.ic_dialog_alert);
		alertbd.setTitle(R.string.wronglogin);
	    alertbd.setMessage(R.string.wrong);
	    alertbd.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // we do nothing...
                // dialog close automatically
	        }
	     });
	    alertbd.create().show();
	}
	
}
class Pair<firstThing, secondThing>{
	private firstThing first;//first member of pair
	private secondThing second;//second member of pair

	public Pair(firstThing first, secondThing second){
		this.first = first;
		this.second = second;
	}

	public void setFirst(firstThing first){
		this.first = first;
	}

	public void setSecond(secondThing second) {
		this.second = second;
	}

	public firstThing getFirst() {
		return this.first;
	}

	public secondThing getSecond() {
		return this.second;
	}
}