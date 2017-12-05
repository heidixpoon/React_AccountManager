import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import * as accountsActions from '../actions/userAccountsActions';
import AccountListItem from '../components/AccountListItem';
import FloatingActionButton from 'material-ui/FloatingActionButton';
import ContentAdd from 'material-ui/svg-icons/content/add';




class AccountsList extends React.Component {
    constructor(props){
        super(props) 
        this.handleClickEach=this.handleClickEach.bind(this)   
        this.handleCreateAccount =this.handleCreateAccount.bind(this)         
        
    }

    componentDidMount(){
        console.log('list renders +++')
        this.props.actions.getAccountsList()
    }

    handleCreateAccount(){
        console.log('dude')
    }

    handleClickEach(event){
        // let encodeUserId = encodeURI(event.target.name)
        let encodeUserId = encodeURIComponent(event.target.name)
        
        console.log(encodeUserId)
        this.props.actions.pushToAccountDetail(encodeUserId, event.target.name)
    }

    render() {
        let {accountsList} = this.props.accountState
        console.log(this)
        console.log(this.handleClickEach)

        
        return (
            <main>
                <h2>Accounts List</h2>
                <FloatingActionButton mini={true} onClick={this.handleCreateAccount}>
                    <ContentAdd />
                </FloatingActionButton>                

                <br/>
                <br/>
                <table>
                    <thead>
                        <tr>
                            <th></th>
                            <th>User ID</th> 
                            <th>Role</th>
                        </tr>
                    </thead>
                    <tbody>

                    { accountsList.map((item, index) => <AccountListItem key={index} item={item} clickEach={this.handleClickEach}/>) }

                    </tbody>

                </table>
                

                
            </main>
        )
    }
}

export default connect(
    state => ({
        appState: state.appReducer,
        accountState: state.userAccountsReducer
    }),
    dispatch => ({
        actions: bindActionCreators({ ...accountsActions }, dispatch)
    })
)(AccountsList);




{/* <div>
    <div className="c-accountList">
        <div className="c-accountList_child_one c-accountList_child"></div>
        <div className="c-accountList_child_two c-accountList_child">User ID</div>
        <div className="c-accountList_child_three c-accountList_child">Role</div>
        <div className="c-accountList_child_four c-accountList_child">Created</div>
    </div>

    { accountsList.map(item => <AccountListItem key={item.id} item={item} />) }

</div> */}