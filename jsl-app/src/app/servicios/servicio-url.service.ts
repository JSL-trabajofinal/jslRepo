import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServicioUrlService {
  private obectSource = new BehaviorSubject<{}>({});
  $getObjectSource = this.obectSource.asObservable();
    

  constructor() { }

  sendObjectSource(data:any){
    this.obectSource.next(data);
  }
}
