import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '././../servicios/login.service';
import { LoadingController, ToastController } from '@ionic/angular';
import { HttpClient } from '@angular/common/http';
import { ServicioUrlService } from '../servicios/servicio-url.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  private loginForm: FormGroup;
  URLServidorInicial : any;
  data: any;
  usuario = '';
  password = '';

  constructor(private http: HttpClient, private activatedRoute: ActivatedRoute,
    private router: Router, private formBuilder: FormBuilder, private loginService: LoginService,
    public toastController: ToastController,
    private servicioUrl: ServicioUrlService,
    public loadingController: LoadingController) { 

      this.loginForm = this.formBuilder.group({
        usuario: ['', Validators.required],
        password: ['', Validators.required],
      });
    }

  ngOnInit() {
    this.servicioUrl.$getObjectSource.subscribe(data  =>  {
      console.log(data)
      this.URLServidorInicial = data;
     }).unsubscribe();
    window.localStorage.URLservidor = this.URLServidorInicial;
  }

  submit() {
    this.presentLoadingWithOptions();
    this.usuario = this.loginForm.controls.usuario.value,
    this.password = this.loginForm.controls.password.value
    console.log('entro ??');
    this.loginService.realizaLogin(this.usuario, this.password)
      .subscribe(
        (response) => {

        
          if (response && response.length) {
            //Guarda el nombre de usuario en cookie
            window.localStorage.usuario = this.usuario;

            //Guarda la autenticacion en cookie
            window.localStorage.autenticacion = btoa(this.usuario + ":" + this.password);
            this.loadingController.dismiss();
            this.router.navigate(['menu/home'])
            console.log('entro');
          }
        },
        (error) => {
          this.loadingController.dismiss();
          console.log(error);
          console.log('Respuesta de la API recibida con error: ' + error.statusText);
          this.loginErroneoToast();
        })
  }

  async loginErroneoToast() {
    const toast = await this.toastController.create({
      message: 'Usuario o Contraseña incorrecto, vuelva a ingresarlos o verifique URL',
      duration: 3500
    });
    toast.present();
  }

  async presentLoadingWithOptions() {
    const loading = await this.loadingController.create({
      //spinner: null,
      //duration: 5000,
      message: 'Iniciando sesión...',
      translucent: true,
      //cssClass: 'custom-class custom-loading'
    });
    return await loading.present();
  }


}
