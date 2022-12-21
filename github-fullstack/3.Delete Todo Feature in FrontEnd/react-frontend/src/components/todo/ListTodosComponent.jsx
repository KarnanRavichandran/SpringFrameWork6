import { useEffect, useState } from "react"
import { deleteTodoApi, retrieveAllTodosForUsernameApi } from "./api/TodoApiService"
import { usernametobepassed } from "./LoginComponent"

function ListTodosComponent() {

    // const today = new Date()
    
    // const targetDate = new Date(today.getFullYear()+12, today.getMonth(), today.getDay())
    
    const[todos,setTodos]=useState([])

    // const todos = [
    //                 // {id: 1, description: 'Learn AWS', done: false, targetDate:targetDate},
    //                 // {id: 2, description: 'Learn Full Stack Dev', done: false, targetDate:targetDate},
    //                 // {id: 3, description: 'Learn DevOps', done: false, targetDate:targetDate},
    //             ]
    
    
    useEffect(
        ()=> refreshTodos() , []
    )
    
    function refreshTodos(){
    retrieveAllTodosForUsernameApi(usernametobepassed)
        .then(  response => { 
            console.log(response.data)
                              setTodos(response.data)
                            }
        )
        .catch((error)=> console.log(error))
    }

    const[message,setMessage] = useState(null)

    function deleteTodo(id){
        deleteTodoApi(usernametobepassed,id)
        .then(
            ()=>{
                setMessage(`Delete Todo ID: ${id} - Successful`)
                refreshTodos()
            }
        )
        .catch((error)=> console.log(error))
    }

    return (
        <div className="container">
            <div  style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}><h1>Things You Want To Do!</h1></div>
            {message && <div  style={{display: 'flex',  justifyContent:'center', alignItems:'center'}} className="alert alert-warning">{message}</div>}
            
            <div  style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}>
                <table className="table">
                    <thead>
                            <tr>
                                <th>Description</th>
                                <th>Is Done?</th>
                                <th>Target Date</th>
                                <th>Delete</th>
                            </tr>
                    </thead>
                    <tbody>
                    {
                        todos.map(
                            todo => (
                                <tr key={todo.id}>                                   
                                    <td>{todo.description}</td>
                                    <td>{todo.done.toString()}</td>
                                    <td>{todo.targetDate.toString()}</td>
                                    <td><button className="btn btn-warning" 
                                        onClick={()=>deleteTodo(todo.id)}>Delete</button></td>
                                </tr>
                            )
                        )
                    }
                    </tbody>

                </table>
            </div>
        </div>
    )
}

export default ListTodosComponent