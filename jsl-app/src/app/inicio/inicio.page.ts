import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ServicioUrlService } from '../servicios/servicio-url.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.page.html',
  styleUrls: ['./inicio.page.scss'],
})
export class InicioPage {

  urlControl = 'http://localhost:8080';

 constructor(
    private router:Router,
    private servicioUrl: ServicioUrlService
  ) {}

  urlServicio(){
    this.servicioUrl.sendObjectSource(this.urlControl);
    this.router.navigate(['/login']);
  }

}
