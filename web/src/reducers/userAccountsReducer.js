import ActionTypes from '../constants/ActionTypes'
import { updateObject, createReducer } from './reducerUtils'

const initialState = {
    accountsList: [],
    currentAccountDetail: {
        id: "",
        account: "",
        role: ""
    }
    
}

const getAccountsList = (state, action) => {
    console.log(action)
    return updateObject(state, {accountsList: action.code})
}

const getAccountDetail = (state, action) => {
    let result = state.accountsList.filter(obj => obj.account === action.code)
    result = result[0]

    return updateObject(state, {currentAccountDetail: result})
}


const asyncValidation = (state, action) => {
    let msg = '';

    switch (action.message) {
        case 'exist':
            // msg = defaultText.hint.existingAccount
            break;
        default:
            break;
    }

    return updateObject(state, {
        message: msg
    })
}
    
    
export default createReducer(initialState, {
    [ActionTypes.REGISTER_ASYNC_VALIDATION]: asyncValidation,
    [ActionTypes.GET_ACCOUNTS_LIST]: getAccountsList,
    [ActionTypes.GET_ACCOUNT_DETAIL]: getAccountDetail
    
    
});