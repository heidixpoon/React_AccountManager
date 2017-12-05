import 'babel-polyfill'
import React from 'react'
import { render } from 'react-dom'
import { Provider } from 'react-redux'
import { Router, hashHistory } from 'react-router'
import { syncHistoryWithStore } from 'react-router-redux'
import { configureStore } from './store'
import './styles/main.css'
import routes from './routes'
require('viewport-units-buggyfill').init()
require('dotenv').config()
// import registerServiceWorker from './registerServiceWorker';

const state = window.__initialState__ || undefined
const store = configureStore(hashHistory, state)
const history = syncHistoryWithStore(hashHistory, store)

render(
	<Provider store={store}>
		<Router history={history} routes={routes}/>
	</Provider>,
	document.getElementById('root')
)
// registerServiceWorker();