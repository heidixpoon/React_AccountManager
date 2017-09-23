import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import AccountSettingForm from '../forms/AccountSettingForm'

import * as accountsActions from '../actions/userAccountsActions';


class AccountDetail extends React.Component {
    constructor(props){
        super(props)
        this.handleSubmit = this.handleSubmit.bind(this);
        
    }

    componentWillMount(){
        this.props.actions.getAccountsList()        
        console.log('get params!  +++')
        // console.log(this.props.params.userId)
        let decodeUserId = decodeURIComponent(this.props.params.userId)
        this.props.actions.getAccountDetail(decodeUserId)
    }

    componentDidMount(){
        console.log('blahh')
        // let decodeUserId = decodeURIComponent(this.props.params.userId)
        // this.props.actions.getAccountDetail(decodeUserId)
    }

    handleSubmit(form){
        console.log(form)
        console.log('submit is triggered')
    }


    render() {
        let {currentAccountDetail} = this.props.accountState
        return (            
                <main>
                    <h2>Account Info</h2>

                    <h4>Email</h4>
                    <p>{currentAccountDetail.account}</p>
                    <h4>First Name</h4>
                    <h4>Last Name</h4>

                    <br/>

                    <h2>Settings</h2>
                    <h4>Role</h4>
                    <AccountSettingForm onSubmit={this.handleSubmit}/>


                
                    <br/>    
                    <h4>Password</h4>
                    <button>Change Password</button>

                    <br />



                    
                </main>

        )
    }
}

export default connect(
    state => ({
        appState: state.appReducer,
        accountState: state.userAccountsReducer
    }),
    dispatch => ({
        actions: bindActionCreators({ ...accountsActions }, dispatch)
    })
)(AccountDetail);


