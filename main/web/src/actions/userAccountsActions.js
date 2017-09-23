import ActionTypes from '../constants/ActionTypes'
import { push } from 'react-router-redux'


export const getAccountsList = () => (dispatch, getState) => {
    const state = getState();
    console.log(state)
    const query = {
        "method": "get"
        };

    return fetch('http://localhost:13332/accounts', query)
    .then( res => res.json() )
        .then( json =>  {
            dispatch({
                type: ActionTypes.GET_ACCOUNTS_LIST,
                code:json
            })    
        })
}


export const pushToAccountDetail = (encodedUserId, nonCodedUserId) => (dispatch, getState) => {
    // dispatch({
    //     type: ActionTypes.GET_ACCOUNT_DETAIL,
    //     code: nonCodedUserId
    // }) 
    dispatch(push(`/accountDetail/${encodedUserId}`));
    
}



export const getAccountDetail = (userId) => (dispatch, getState) => {
    dispatch({
        type: ActionTypes.GET_ACCOUNT_DETAIL,
        code: userId
    })     
}





// export const editTemplate = (templateId, clientId) => dispatch => {
//     dispatch(initialStatus(true, clientId, templateId));
//     dispatch(push(`/emailForm/${clientId}/${templateId}`));
// }



// return fetch('http://localhost:13332/accounts', query)
// return fetch('http://192.168.1.31:8080/api/v1/users', query)
