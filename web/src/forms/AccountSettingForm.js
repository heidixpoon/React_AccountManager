import React from 'react'
import { connect } from 'react-redux'
import { Field, reduxForm } from 'redux-form'


const roles = ['Admin', 'General User']


let AccountSettingForm = props => {
  const { handleSubmit } = props
  
  return (
    <form onSubmit={ handleSubmit }>
      <div>
        <Field name="role" component="select">
          <option value="general user">General User</option>
          <option value="admin">Admin</option>            
        </Field>
      </div>
       
      <button type="submit">Update Role</button>
    </form>
  )
}

AccountSettingForm = reduxForm({
  form: 'accountSetting'
})(AccountSettingForm)


AccountSettingForm = connect(
  state => ({
    initialValues: {
      role: state.userAccountsReducer.currentAccountDetail.role 
    }// pull initial values from reducer
  })
)(AccountSettingForm)


export default AccountSettingForm;