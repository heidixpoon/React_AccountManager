import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import SignInForm from '../forms/SignInForm'
import * as appActions from '../actions/appActions';


class SignIn extends React.Component {
    constructor(props){
        super(props)
        this.submit = this.submit.bind(this)
        this.sayHi = this.sayHi.bind(this)
    }

    submit(value){
        console.log(this)
        this.props.actions.helloWorld()
    }

    sayHi() {
        this.props.actions.sayHi()
    }

    render() {
        console.log(this.props.appState)
        
        return (
            <main>
                <SignInForm onSubmit={this.submit} />
                <button onClick={this.sayHi}>Say Hi</button>
                {this.props.appState.helloWorld}
            </main>
        )
    }
}

export default connect(
    state => ({
        appState: state.appReducer
    }),
    dispatch => ({
        actions: bindActionCreators({ ...appActions }, dispatch)
    })
)(SignIn);