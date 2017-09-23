import { createStore, combineReducers, applyMiddleware } from 'redux'
// import { compose } from 'redux'
import { routerReducer, routerMiddleware } from 'react-router-redux'
import thunk from 'redux-thunk'

import { reducer as formReducer } from 'redux-form'
import appReducer from './reducers/appReducer'
import userAccountsReducer from './reducers/userAccountsReducer'

export const configureStore = (history, initialState) => {
  const reducer = combineReducers({
    appReducer,
    userAccountsReducer,
    form: formReducer,
    routing: routerReducer
  })


  const store = createStore(
    reducer,
    initialState,
    applyMiddleware(
      thunk,
      routerMiddleware(history)
    ),
    window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
  )

  // const composeEnhancers = process.env.REACT_APP_ENV
  //   ? window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose
  //   : compose


  // const store = createStore(
  //   reducer,
  //   initialState,
  //   composeEnhancers(
  //     applyMiddleware(
  //       thunk,
  //       routerMiddleware(history)
  //     ))
  // )

  return store
}
