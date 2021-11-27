import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-cuadrilla-detalle',
  templateUrl: './cuadrilla-detalle.page.html',
  styleUrls: ['./cuadrilla-detalle.page.scss'],
})
export class CuadrillaDetallePage{
  id_Cuadrilla;
  datosReclamo;
  cuadrillas: any;
  param: any;
  url = environment.urlServidor;
  
  constructor(
    private http: HttpClient,
    private activatedRoute: ActivatedRoute
  ) {}
  
  
  public contenidoArray: any = null;
  
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
    });
  
    
    }
  
  
   
  }
  
  
