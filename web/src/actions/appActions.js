import ActionTypes from '../constants/ActionTypes'

export const helloWorld = () => ({ type: ActionTypes.HELLO_WORLD })


export const sayHi = () => (dispatch, getState) => {

  const state = getState();
  console.log(state)

  dispatch({type: ActionTypes.HELLO_WORLD})
  return fetch('http://localhost:13332/signIn', {method: 'get'})
    .then (
      res=> {
        console.log(res)
        return dispatch({type: ActionTypes.SAY_HI})
      }
    )

}

// dispatch({type: ActionTypes.SAY_HI});console.log(res)