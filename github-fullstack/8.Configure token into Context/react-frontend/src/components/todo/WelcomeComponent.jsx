import { useState } from 'react'
import {Link} from 'react-router-dom'
import { usernametobepassed } from './LoginComponent'
// import { retrieveHelloWorldBean } from './api/HelloWorlApiService'
import { retrieveHelloWorldPathVariable } from './api/HelloWorlApiService'
import { AuthContext, useAuth } from './security/AuthContext'

function WelcomeComponent() {

    const[message,setMessage]=useState(null)
    const authContext=useAuth()
    function helloworldrestapi(){
        console.log("clicked")
        
        retrieveHelloWorldPathVariable(usernametobepassed,authContext.token)
            .then((response)=>successfulResponse(response))
            .catch((error)=>errorResponse(error))
            .finally(()=>console.log('cleanup'))
    }

     
   

    function successfulResponse(response){
        console.log(response)
       
        setMessage(response.data.message)
    }
    function errorResponse(error){
        console.log(error)
    }
    return (
        <div className="WelcomeComponent">
            <div style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}><h1>Welcome {usernametobepassed}</h1></div>
            <div style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}>
                Manage your todos - <Link to="/todos">Go here</Link>
            </div>
            <br></br>
            <div style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}>
            <button className='btn btn-success' onClick={helloworldrestapi}>call hello world rest api</button>
            </div>
            <div style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}>
                {message}
            </div>
        </div>
    )
}

export default WelcomeComponent