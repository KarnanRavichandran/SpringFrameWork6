function ErrorComponent() {
    return (
        <div className="ErrorComponent" style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}>
            <h1>The resource you're trying to reach is unavailable !</h1>
            <div>
                Contact us <a href="dew">Click here</a>
            </div>
        </div>
    )
}

export default ErrorComponent