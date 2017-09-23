import React from 'react'
import { Field, reduxForm } from 'redux-form'
import InputPassword from '../components/InputPassword'

let SignInForm = props => {
  const { handleSubmit } = props
  return (
    <form onSubmit={ handleSubmit }>
       <div>
        <label htmlFor="account">Account</label>
        <Field name="account" component="input" type="text" />
      </div>
      <div>
        <label htmlFor="password">Password</label>
        <Field name="password" component={InputPassword}/>
      </div>
      <button type="submit">SignIn</button>
    </form>
  )
}

SignInForm = reduxForm({
  form: 'signIn'
})(SignInForm)

export default SignInForm;