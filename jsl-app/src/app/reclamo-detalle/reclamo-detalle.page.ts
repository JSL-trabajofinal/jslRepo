import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-reclamo-detalle',
  templateUrl: './reclamo-detalle.page.html',
  styleUrls: ['./reclamo-detalle.page.scss'],
})
export class ReclamoDetallePage implements OnInit {
  id_Reclamo;
  datosReclamo;
  datosCuadrilla;
  param: any;
  url = environment.urlServidor;

  constructor(
    private http: HttpClient,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.param = this.activatedRoute.snapshot.params;
    if (Object.keys(this.param).length) {
      this.detallarReclamo(this.param.id_Reclamo);
    }
  }
  detallarReclamo(id_Reclamo) {
    const httpOptions = {
      headers: new HttpHeaders({
        Accept: 'application/json;profile=urn:org.apache.isis/v1',
        Authorization: 'Basic YWRtaW46cGFzcw==',
      }),
    };
    const URL1 = this.url+'/restful/objects/simple.Reclamo/' + id_Reclamo;
    this.http.get(URL1, httpOptions).subscribe((resultados) => {
      this.datosReclamo = resultados;
    });

    const URL2 = this.url+'/restful/objects/simple.Cuadrilla/' + id_Reclamo;
    this.http.get(URL2, httpOptions).subscribe((resultados2) => {
      this.datosCuadrilla = resultados2;
    });

  }
}
