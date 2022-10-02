import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {AuthService} from '../../authentication/service/auth.service';
import {User} from '../../authentication/model/user';
import {ChatService} from '../service/chat.service';
import {of} from 'rxjs';
import {Router} from '@angular/router';
import {Message} from '../model/message';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  @ViewChild('chathistory') chathistory: ElementRef;
  scrolltop: number = null;
  userName = '';
  users: User[];
  messages: Message[];
  senderId: number;
  receiverId: number;
  receiver: User;
  message = '';

  constructor(private chatService: ChatService,
              private route: Router) {
    this.chatService.updateContents.subscribe(message => this.getMessagesByConversation());
  }

  ngOnInit(): void {
    this.checkLogin();
    this.changeInfo();
    this.getAllUsers();
  }

  private changeInfo() {
    this.userName = localStorage.getItem('username');
    this.senderId = Number(localStorage.getItem('userid'));
  }

  getAllUsers() {
    this.chatService.getAllUsers().subscribe((users: any) => {
      this.users = users;
    }, error => {
      console.log('error getting users');
    });
  }

  checkLogin() {
    if (localStorage.getItem('username') === null) {
      console.log('--check login--');
      this.route.navigateByUrl('/sign-in');
    } else {
      console.log('--chuan bi connect ne--');
      this.chatService.connect();
    }
  }

  getMessagesByConversation() {
    this.chatService.getMessagesByConversation(this.senderId, this.receiverId).subscribe(messages => {
      this.messages = messages;
      setTimeout(() => this.scrolltop = this.chathistory.nativeElement.scrollHeight, 10);
    }, error => {
      console.log('error getting msg');
    });
  }

  setReceiver(user: User) {
    this.receiverId = user.userId;
    this.receiver = user;
    this.getMessagesByConversation();
  }

  sendMessage() {
    const sendingMessage = {
      senderId: this.senderId,
      receiverId: this.receiverId,
      content: this.message
    };
    this.chatService.sendMessage(sendingMessage).subscribe(res => {
      console.log('send msg successfully');
      this.message = '';
    }, error => {
      console.log('send msg failed');
    });
  }
}
