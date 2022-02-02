import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ServicioUrlService } from '../servicios/servicio-url.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  ConversionBase64: string;

  constructor(private http: HttpClient,
    private servicioUrl: ServicioUrlService) { }

  url : any;

  realizaLogin(usuario:String, contrasena:String){
    this.servicioUrl.$getObjectSource.subscribe(data  =>  {
      console.log(data);
      this.url = data;
     }).unsubscribe();

  this.ConversionBase64 = btoa(usuario+":"+contrasena);

      let headers: HttpHeaders = new HttpHeaders();
      headers = headers.append('Accept', 'application/json;profile=urn:org.apache.isis/v1');
      headers = headers.append('Authorization', 'Basic '+this.ConversionBase64);

    const URL = this.url+'/restful/services/simple.SimpleReclamoMenu/actions/listAll/invoke';

    return this.http.get<any>(URL, {headers: headers})  

  } 
  
}
