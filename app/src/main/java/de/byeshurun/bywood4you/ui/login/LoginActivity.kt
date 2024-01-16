package de.byeshurun.bywood4you.ui.login
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.byeshurun.bywood4you.R
import de.byeshurun.bywood4you.logic.database.DbManager
import de.byeshurun.bywood4you.ui.dashboard.DashboardActivity
import de.byeshurun.bywood4you.ui.theme.BYWood4YouTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BYWood4YouTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginCheck()
                }
            }
        }
    }
}

@Composable
fun LoginCheck() {

    var userName by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var loginInfo by remember { mutableStateOf("") }

    val userOne = "Me"
    val passOne = "Volkswagen1"

    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Image(
            painterResource(id = R.drawable.woodlogo),
            contentDescription = stringResource(R.string.wood4ulogo),
            modifier = Modifier
                .height(200.dp)
                .fillMaxSize()
        )
        Text(stringResource(R.string.login))
        BasicTextField(
            value = userName,
            onValueChange = { userName = it },
            decorationBox = {
                if (userName.isEmpty()) {
                    Text(
                        stringResource(R.string.userName),
                        color = Color.Gray,
                        modifier = Modifier.padding(8.dp),
                        textAlign = TextAlign.Center

                    )
                }
                it()
            },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            modifier = Modifier
                .padding(16.dp)
                .border(2.dp, MaterialTheme.colorScheme.onSurface, RoundedCornerShape(4.dp))
                .height(48.dp)
                .fillMaxSize()

        )

        BasicTextField(
            value = userPassword,
            onValueChange = { userPassword = it },
            decorationBox = {
                if (userPassword.isEmpty()) {
                    Text(
                        stringResource(R.string.second_input),
                        color = Color.Gray,
                        modifier = Modifier.padding(8.dp),
                        textAlign = TextAlign.Center
                    )
                }
                it()
            },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            modifier = Modifier
                .padding(16.dp)
                .border(2.dp, MaterialTheme.colorScheme.onSurface, RoundedCornerShape(4.dp))
                .height(48.dp)
                .fillMaxSize()

        )

        Button(
            onClick = {
                if (userPassword.length > 8) {
                    if (userName.isNotEmpty() && userPassword.isNotEmpty()) {
                        if (DbManager.getInstance(context).checkUser(userName, userPassword)) {
                            val intentToStartDashboardActivity = Intent(context, DashboardActivity::class.java)
                        context.startActivity(intentToStartDashboardActivity)
                    } else {
                            loginInfo = context.getString(R.string.login_failed);
                        }
                    } else {
                        loginInfo = context.getString(R.string.invalid_password);
                    }
                } else {
                    loginInfo = context.getString(R.string.enter_both);
                        }
                    },
        ) { Text(stringResource(R.string.login)) }

        Text(loginInfo)
    }

}

@Preview(showBackground = true)
@Composable
fun LoginGuiPreview() {
    BYWood4YouTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            LoginCheck()
        }
    }
}