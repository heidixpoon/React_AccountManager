import React from 'react';

class AccountListItem extends React.Component {


    render() {
        let { item , clickEach} = this.props
        return (
            <tr className="" key={item}>
                <th className="c-accountList_child_one"></th>
                <th className="c-accountList_child_two" name={item.id}>{item.account}</th>
                <th className="c-accountList_child_three" name={item.id}>{item.role}</th>
                <th className="c-accountList_child_four"><button name={item.account} onClick={clickEach}>Edit</button></th>
            </tr>
        );
    }
}

export default AccountListItem;



// <div className="" key={item.id}>
// <div className="c-accountList_child_one"></div>
// <div className="c-accountList_child_two" name={item.id}>{item.userID}</div>
// <div className="c-accountList_child_three" name={item.id}>{item.role}</div>
// <div className="c-accountList_child_four" name={item.id}>{item.created}</div>
// </div>