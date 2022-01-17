import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class PlanillaService {

  servidorUrl = environment.urlServidor;

  constructor(private httpClient: HttpClient) { }
  
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept':  'application/json;profile=urn:org.apache.isis/v1',
      'Authorization': 'Basic aXNpcy1tb2R1bGUtc2VjdXJpdHktYWRtaW46cGFzcw==',
    }),
  };

  private Url = this.servidorUrl + '/restful/objects/simple.Reclamo/';
  private urlPlanilla = this.servidorUrl + '/restful/objects/simple.PlanillaCuadrilla/';


  getPlanillas(id: number) {
    console.log('id de getPlanilla ' + id);
    return this.httpClient.get(this.urlPlanilla + id, this.httpOptions);
  }


  crearPlanilla(id, planilla) {
    console.log('Ingresando a crearPlanilla ' + id);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Accept': 'application/json;profile=urn:org.apache.isis/v1',
        'Authorization': 'Basic YWRtaW46cGFzcw==',
      }),
    };

    const crearPlanillaUrl =
      this.servidorUrl + '/restful/objects/simple.Reclamo/';

    let datos = {
      "seRealizoConexion": {
        value: planilla.seRealizoConexion,
      },
      "seCambioConexion": {
        value: planilla.seCambioConexion,
      },
      "seReparoConexion": {
        value: planilla.seReparoConexion,
      },
      "seAnuloConexion": {
        value: planilla.seAnuloConexion,
      },
      "seDestapoRed": {
        value: planilla.seDestapoRed,
      },
      "colectoraNivelAlto": {
        value: planilla.colectoraNivelAlto,
      },
      "problemaInterno": {
        value: planilla.problemaInterno,
      },
      "observacion": {
        value: planilla.observacion,
      },
    };
    console.log(datos);
    return this.httpClient.post(
      crearPlanillaUrl + id + '/actions/addPlanilla/invoke',
      JSON.stringify(datos),
      httpOptions
    );
  }

  cerrarReclamo(id) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Accept': 'application/json;profile=urn:org.apache.isis/v1',
        'Authorization': 'Basic YWRtaW46cGFzcw==',
      }),
    };
    const cerrarReclamoUrl =
      this.servidorUrl + '/restful/objects/simple.Reclamo/';
    return this.httpClient.put(
      cerrarReclamoUrl + id + '/actions/Cerrar/invoke',{},
      httpOptions
    );
  }


}
