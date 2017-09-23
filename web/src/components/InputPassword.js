import React from 'react'

const InputPassword = field => (
    <div className="input-row">
      <input {...field.input} type="password"/>
      {field.meta.touched && field.meta.error && 
       <span className="error">{field.meta.error}</span>}
    </div>
)

export default InputPassword