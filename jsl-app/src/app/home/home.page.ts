import { Component } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
export class HomePage {

  constructor(public router: Router) {}

  alertaLogOut(){
    Swal.fire({
      title: "Esta seguro que desea cerrar la sesión",
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: "Si",
      confirmButtonColor: "#68DE65",
      cancelButtonText: "No",
      cancelButtonColor: "#DF4343",
  })
  .then(resultado => {
      if (resultado.isConfirmed) {
        Swal.fire({
          title: "Sesión cerrada exitosamente!",
                icon: 'success', 
                confirmButtonColor: "#68DE65",               
              })
          this.router.navigate(['/login']);
      } else {
        Swal.fire({
          title: "Acción cancelada ", 
          confirmButtonColor: "#68DE65",
          icon: 'error',
                 })
      }
  });
    }
  
  }