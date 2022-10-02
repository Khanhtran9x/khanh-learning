import {EventEmitter, Injectable, Output} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Message} from '../model/message';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {User} from '../../authentication/model/user';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

const API_URL = `${environment.apiUrl}`;

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  webSocketEndPoint = 'http://localhost:8080/ws-chat';
  topic = '/topic/chats';
  stompClient = null;
  name = '';
  @Output() updateContents: EventEmitter<any> = new EventEmitter<any>();

  constructor(private http: HttpClient) {
  }

  connect() {
    console.log('Initialize WebSocket Connection');
    const ws = new SockJS(this.webSocketEndPoint);
    this.stompClient = Stomp.over(ws);
    this.stompClient.connect({}, frame => {
      this.stompClient.subscribe(this.topic, chat => {
        console.log('nhan dc thay roi tu spring');
        this.onMessageReceived(JSON.parse(chat.body));
      });
    }, this.errorCallBack);
  }

  sendMessage(message: Message): Observable<Message> {
    return this.http.post<any>(API_URL + '/send', message);
  }

  onMessageReceived(message: Message) {
    console.log('ChatMessage received from Server: ' + message.content);
    this.updateContents.emit();
  }

  errorCallBack(error) {
    console.log('errorCallBack -> ' + error);
    setTimeout(() => {
      this.connect();
    }, 5000);
  }

  getMessagesByConversation(senderId: number, receiverId: number): Observable<Message[]> {
    return this.http.get<Message[]>(API_URL + '/messages?senderId=' + senderId + '&receiverId=' + receiverId);
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(API_URL + '/users');
  }
}
