import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import { PlanillaService } from './../servicios/planilla.service';
import { environment } from 'src/environments/environment';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-crear-planilla',
  templateUrl: './crear-planilla.page.html',
  styleUrls: ['./crear-planilla.page.scss'],
})
export class CrearPlanillaPage implements OnInit {

 
  servidorUrl = environment.urlServidor;

  planillaForm: FormGroup;
  id;
  idReclamo;
  idPlanilla;
  datosPlanilla;
  editable: boolean = false;
  verPlanilla: boolean = true;
  param: any;
  planillas: any[] = [];


  constructor(
    private http: HttpClient,
    private activatedRoute: ActivatedRoute,
    private paramRoute: ActivatedRoute,
    private router: Router,
    private planillaService: PlanillaService,
    private fb: FormBuilder
  ) {
    this.planillaForm = this.fb.group({
      seRealizoConexion: ['No',Validators.required],
      seCambioConexion: ['No',Validators.required],
      seReparoConexion: ['No',Validators.required],
      seAnuloConexion: ['No',Validators.required],
      seDestapoRed: ['No',Validators.required],
      colectoraNivelAlto: ['No',Validators.required],
      problemaInterno: ['No',Validators.required],
      observacion: [' ',Validators.required],
    });
  }


  ngOnInit() {
    this.param = this.activatedRoute.snapshot.params;
    if (Object.keys(this.param).length) {
      // this.getPlanilla(this.param.idReclamo);
    this.idReclamo = this.param.idReclamo;
    this.idPlanilla = this.param.idPlanilla;  
  }
  }

  
  detallarPlanilla(idPlanilla) {
    console.log('En detallarPlanilla ' + idPlanilla);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Accept': 'application/json;profile=urn:org.apache.isis/v1',
        'Authorization': 'Basic YWRtaW46cGFzcw==',
      }),
    };
    const URL = this.servidorUrl + '/restful/objects/simple.PlanillaCuadrilla/' +
      idPlanilla;
      this.http.get(URL, httpOptions).subscribe((resultados) => {
      this.datosPlanilla = resultados;
    }); 
  }


    getPlanilla(idReclamo) {
      console.log('En getPlanilla ' + idReclamo);
      this.planillaService.getPlanillas(idReclamo).subscribe((planillas: any) => {
        this.datosPlanilla = planillas;
        this.planillaForm.patchValue(this.datosPlanilla);
        console.log(this.datosPlanilla);
      }); 
    }


  submit() {
    Swal.fire({
      title: "Desea confirmar la carga de planilla, recuerde que esta acción también dara cierre al reclamo",
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: "Si",
      confirmButtonColor: "#68DE65",
      cancelButtonText: "No",
      cancelButtonColor: "#DF4343",
  })
  .then(resultado => {
      if (resultado.isConfirmed) {

        this.planillaService
          .crearPlanilla(this.idReclamo, this.planillaForm.value)
          .subscribe((planilla) => {
      
          });
          this.planillaService
          .cerrarReclamo(this.idReclamo).subscribe((planilla) => {
          });
          this.router.navigate(['/menu/cuadrillas']);
           Swal.fire({
            title: "Carga existosa",
                  icon: 'success', 
                  confirmButtonColor: "#68DE65",               
                })
                this.planillaForm.reset(); 
      } else {
        Swal.fire({
          title: "Carga Cancelada ", 
          confirmButtonColor: "#68DE65",
          icon: 'error',
                 })
      }
  });

  }


}