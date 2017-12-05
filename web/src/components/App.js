import React from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';


const App = props => {
    return(
        <MuiThemeProvider>        
            <div>{props.children}</div>
        </MuiThemeProvider>        

    )
}

export default App;
