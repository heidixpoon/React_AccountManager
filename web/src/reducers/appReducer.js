import ActionTypes from '../constants/ActionTypes'
import { updateObject, createReducer } from './reducerUtils'

const initialState = {
    helloWorld: '* _____ *'
}

const helloWorld = (state, action) => updateObject(state, {
    helloWorld: 'Wow! New World' 
})

const sayHi = (state, action) => updateObject(state, {
    helloWorld: 'Hi' 
})


export default createReducer(initialState, {
    [ActionTypes.HELLO_WORLD]: helloWorld,
    [ActionTypes.SAY_HI]: sayHi,
   
})
