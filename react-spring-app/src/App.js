import React , { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import {User} from './Service/ClassUser';
import {DataTable} from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Menubar } from 'primereact/menubar';
import "primereact/resources/primereact.min.css"; 
import "primereact/resources/themes/mdc-dark-indigo/theme.css";  
import { Panel } from 'primereact/panel';
import { Dialog } from 'primereact/dialog';
import { InputText } from 'primereact/inputtext';
import { Password } from 'primereact/password';
import { Button } from 'primereact/button';

export default class App extends Component{

  constructor(){
    super();
    this.state = {
      visible : false,
      user : {
        id_user : null,
        email_address : null,
        password : null,
        enabled : null,
        nickname : null,
        img : null,
        solved : 0,
      },
    };
    this.items = [
      {
        label: 'NEW',
        icon : 'pi pi-fw pi-plus',
        command : () => this.ShowDialog(),
      },
      {
        label : 'EDIT',
        icon : 'pi pi-fw pi-pencil',
        command : () => {alert('EDITED')}
      },
      {
        label : 'DELETE',
        icon : 'pi-trash',
        command : () => {alert('DELETED')}
      },
    ];

    this.User = new User();
    this.save = this.save.bind(this);

    this.footer = (
      <div>
        <Button label="Save" onClick={this.save} />
      </div>
    );
    
  }
  componentDidMount(){
    this.User.getAll().then(data => this.setState({users : data}))
    this.setState({

    });
  }

  save() {
    this.User.save(this.state.user).then( data => {console.log(data)})
  }

  render(){
    return (
      <div style = {{width : '80%' , marginTop : '50px' , margin : '0 auto'} }>
        <br/>
        <Menubar model = {this.items} />
        <br/>
        <Panel header="USERS">
          <DataTable value = {this.state.users}>
            <Column field="id_user" header="ID"> </Column>
            <Column field="email_address" header="Email"> </Column>
            <Column field="nickname" header="Nickname"> </Column>
            <Column field="solved" header="Solved"> </Column>
          </DataTable>
        </Panel>

      <Dialog header="New User" footer = {this.footer} visible={this.state.visible} style={{ width: '60%' }} modal = {true} onHide ={()=>this.setState({visible: false})}>
          
          <br></br>
          <span className="p-float-label">
            
            <InputText id="email_address" value={this.state.user.email_address} onChange={(e) => this.setState(prevState => {
              let us = Object.assign({},prevState.user); // us is a user object
              us.email_address = e.target.value;
              this.state.user.email_address = us.email_address; 
              return {us}
            })} />

            <label htmlFor="email_address">Username</label>
          </span>

          <br></br>
          <span className="p-float-label">
            <InputText id="nickname" value={this.state.value} onChange={(e) => this.setState(prevState => {
              let us = Object.assign({},prevState.user); // us is a user object
              us.nickname = e.target.value;
              this.state.user.nickname = us.nickname;
              return {us}
            })} />
            <label htmlFor="in">nickname</label>
          </span>

          <br></br>
          <span className="p-float-label">
            <InputText id="password" value={this.state.value} onChange={(e) => this.setState(prevState => {
              let us = Object.assign({},prevState.user); // us is a user object
              us.password = e.target.value;
              this.state.user.password = us.password;
              console.log(this.state.user);
              return {us}
            })} />
            <label htmlFor="in">password</label>
          </span>

      </Dialog>
      
      </div>
    );
  }

  ShowDialog() {
    this.setState ({
      visible : true,
    })
  }
}