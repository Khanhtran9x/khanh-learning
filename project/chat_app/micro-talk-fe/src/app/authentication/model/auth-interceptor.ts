import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let modifiedReq = req;
    if (localStorage.getItem('username') && localStorage.getItem('token')) {
      console.log('check header');
      modifiedReq = req.clone({
        setHeaders: {
          Authorization: localStorage.getItem('token'),
          'Access-Control-Allow-Origin': '*',
          'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS',
        }
      });
    }
    console.log(modifiedReq);
    return next.handle(modifiedReq);
  }
}
