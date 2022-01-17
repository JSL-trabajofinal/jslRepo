import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { environment } from 'src/environments/environment';


@Component({
  selector: 'app-cuadrilla-detalle',
  templateUrl: './cuadrilla-detalle.page.html',
  styleUrls: ['./cuadrilla-detalle.page.scss'],
})
export class CuadrillaDetallePage{
  id;
  idPlanilla;
  datosReclamo;
  cuadrillas: any;
  param: any;
  resultadosArraytemp: any;
  url = environment.urlServidor;
  
  constructor(
    private http: HttpClient,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}
  
  
  public contenidoArray: any = null;
  public resultadosArrayFiltrado = [];
  
  ngOnInit() {
    this.param = this.activatedRoute.snapshot.params;
    if (Object.keys(this.param).length) {
      this.detallarCuadrilla(this.param.id_Cuadrilla);
    }
  }


    detallarCuadrilla(id_Cuadrilla) {
      const httpOptions = {
        headers: new HttpHeaders({
          Accept: 'application/json;profile=urn:org.apache.isis/v1',
          Authorization: 'Basic YWRtaW46cGFzcw==',
        }),
      };
      const URL = this.url+'/restful/objects/simple.Cuadrilla/' + id_Cuadrilla + '/collections/reclamosAsignados';
      this.http.get(URL, httpOptions).subscribe((cuadrillas: Array<any>) =>  {
        var array = cuadrillas;
        array.pop();
        this.contenidoArray = array;
        this.resultadosArraytemp = this.contenidoArray;
    
        const largoArray = this.resultadosArraytemp.length;
  
        for (var i = 0; i < largoArray; ) {
          if (this.resultadosArraytemp[i].hasOwnProperty('estado')) {
            if (this.resultadosArraytemp[i].estado != 'Cerrado' && this.resultadosArraytemp[i].estado != 'Anulado') {
              this.resultadosArrayFiltrado.push(this.contenidoArray[i]);
            }
          }
          i = i + 1;
        }
        this.resultadosArrayFiltrado.sort(((a, b) => a.nroReclamo - b.nroReclamo));
        this.contenidoArray = this.resultadosArrayFiltrado;
      });
    }


    cargarPlanilla(id) {
      console.log("ingresando a cargarPlanilla " + id);
      this.router.navigate(['/crear-planilla', { idReclamo: id }]);
    }

    obtenerPlanilla(idPlanilla) {
      console.log("ingresando a obtenerPlanilla " + idPlanilla);
      this.router.navigate(['/planilla', { id: idPlanilla }]);
    }
   
  }