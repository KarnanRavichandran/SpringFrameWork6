import { ErrorMessage, Field, Form, Formik } from "formik"
import { useEffect, useState } from "react"
import { useParams } from "react-router-dom"
import { retrieveTodoApi } from "./api/TodoApiService"
import { usernametobepassed } from "./LoginComponent"
export default function TodoComponent(){
    
    const {id} =useParams()
    const[description,setDescription]=useState('')
    const[targetDate,setTargetDate]=useState('')

    function retrieveTodos(){
        retrieveTodoApi(usernametobepassed,id)
         .then((response)=>{
            setDescription(response.data.description)
            setTargetDate(response.data.targetDate)
         })
         .catch((error)=>console.log(error))
    }
    
    useEffect(
       ()=> retrieveTodos(), [id]
    );

    function onSubmit(values){
        console.log(values)
    }
    function validate(values){
        let errors ={}
            if(values.description.length<5)
            {
                errors.description ="Enter a valid description with atleast 5 characters!!"
            }
            if(values.targetDate==null)
            {
                errors.targetDate ="Enter a valid target Date"
            }
            console.log(values)
            return errors
    }

    return(
        <div className="container">
            <div style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}>
                <h1>Please update the details</h1>
            </div>
           
            <div>
               
               <Formik initialValues={{description,targetDate}} 
                   enableReinitialize={true} onSubmit= {onSubmit} validate={validate} validateOnBlur={false} validateOnChange={false}>
                    { (props)=>(
                                <Form>
                                    <ErrorMessage
                                     name="description"
                                     component="div"
                                     className="alert alert-warning"
                                    />
                                     <ErrorMessage
                                     name="targetDate"
                                     component="div"
                                     className="alert alert-warning"
                                    />

                                    <fieldset className="form-group">
                                        <label>Description</label>
                                        <Field style={{display: 'flex',  justifyContent:'center', alignItems:'center'}} 
                                            type="text" className="form-control" name="description"/>
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Target Date</label>
                                        <Field style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}
                                            type="date" className="form-control" name="targetDate"/>
                                    </fieldset>
                                    <div style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}>
                                        <button className="btn btn-success m-5" type="submit">Save</button>
                                    </div>
                                </Form>
                    )
               }
               </Formik>
            </div>
        </div>
    )
}