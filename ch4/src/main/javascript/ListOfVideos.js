import React from "react"

export const ListOfVideos = () => {

    const [data, setData] = React.useState([])

    const handleFetch = async () => {
        let json = await fetch("/api/videos").json()
        setData(json)
    }

    React.useEffect(() => {
        handleFetch()
    }, [handleFetch])

    return (
        <ul>
            {data.map((elt, index) => {
                <li key={index}>{elt.name}</li>
            })}
        </ul>
    )
}